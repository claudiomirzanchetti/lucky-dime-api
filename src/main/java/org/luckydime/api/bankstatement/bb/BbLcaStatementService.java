package org.luckydime.api.bankstatement.bb;

import lombok.RequiredArgsConstructor;
import org.luckydime.api.financialasset.FinancialAssetService;
import org.luckydime.api.investmentposition.InvestmentPosition;
import org.luckydime.api.util.CsvUtils;
import org.luckydime.api.util.FileUtils;
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
public class BbLcaStatementService {
    private final FinancialAssetService financialAssetService;

    public List<InvestmentPosition> getInvestmentPositions(LocalDate statementDate) {
        var investmentFunds = new ArrayList<InvestmentPosition>();
        File investmentFundsFile = FileUtils.getStatementFile("bb-lca");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));

        CsvUtils.getLinesFromFileUsingCharset(investmentFundsFile, Charset.forName("windows-1252"))
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
                                            investmentFunds.add(InvestmentPosition.builder()
                                                    .financialAsset(financialAsset)
                                                    .positionDate(statementDate)
                                                    .positionValue(position)
                                                    .build());
                                        }
                                    } catch (ParseException e) {
                                        throw new RuntimeException("Unable to parse position. nameInStatement: " + nameInStatement, e);
                                    }
                                });
                    }
                });

        return investmentFunds;
    }
}