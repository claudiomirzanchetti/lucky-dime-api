package org.luckydime.api.bankstatement.bb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.luckydime.api.financialasset.FinancialAssetService;
import org.luckydime.api.investmentposition.InvestmentPosition;
import org.luckydime.api.util.CsvUtil;
import org.luckydime.api.util.ExceptionUtil;
import org.luckydime.api.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class BbLcaStatementService {
    private final FinancialAssetService financialAssetService;

    public List<InvestmentPosition> getInvestmentPositions(LocalDate statementDate) {
        log.info("Getting investment positions from BB LCA statement.");

        var investmentPositions = new ArrayList<InvestmentPosition>();
        File investmentFundsFile = FileUtil.getStatementFile("bb-lca");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));

        CsvUtil.getLinesFromFileUsingCharset(investmentFundsFile, Charset.forName("windows-1252"))
                .forEach(l -> {
                    if (l.length() == 100) {
                        if (l.contains("SALDO")) {
                            return;
                        }

                        var nameInStatement = l.substring(1, 21).trim();

                        if (isEmpty(nameInStatement)) {
                            return;
                        }

                        financialAssetService.findByNameInStatementOrderByNameAndOrderPresentInTheStatement(nameInStatement).stream()
                                .findFirst()
                                .ifPresent(financialAsset -> {
                                    try {
                                        var position = numberFormat.parse(l.substring(64, 75).trim()).doubleValue();

                                        if (position > 0) {
                                            investmentPositions.add(InvestmentPosition.builder()
                                                    .financialAsset(financialAsset)
                                                    .positionDate(statementDate)
                                                    .positionValue(position)
                                                    .build());
                                        }
                                    } catch (ParseException e) {
                                        ExceptionUtil.logErrorAndThrowException("Unable to parse position. nameInStatement: " + nameInStatement, e);
                                    }
                                });
                    }
                });

        log.info("{} investment positions found.", investmentPositions.size());

        return investmentPositions;
    }
}