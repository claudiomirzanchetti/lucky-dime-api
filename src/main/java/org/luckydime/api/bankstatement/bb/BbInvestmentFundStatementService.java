package org.luckydime.api.bankstatement.bb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.luckydime.api.financialasset.FinancialAsset;
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
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class BbInvestmentFundStatementService {
    private final FinancialAssetService financialAssetService;

    public List<InvestmentPosition> getInvestmentPositions(LocalDate statementDate) {
        log.info("Getting investment positions from BB investment fund statement.");

        var investmentPositions = new ArrayList<InvestmentPosition>();
        File investmentFundsFile = FileUtil.getStatementFile("bb-fi");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        final AtomicReference<FinancialAsset> financialAsset = new AtomicReference<>();

        CsvUtil.getLinesFromFileUsingCharset(investmentFundsFile, Charset.forName("windows-1252"))
                .forEach(l -> {
                    var nameInStatement = l.length() > 66 ? l.substring(0, 66).trim() : null;

                    if (isNull(financialAsset.get()) && nonNull(nameInStatement)) {
                        financialAssetService.findByNameInStatementOrderByNameAndOrderPresentInTheStatement(nameInStatement).stream()
                                .findFirst()
                                .ifPresent(fa -> financialAsset.set(fa));
                    }

                    if (nonNull(financialAsset.get()) && l.contains("SALDO ATUAL        =")) {
                        try {
                            var position = numberFormat.parse(l.substring(116).trim()).doubleValue();

                            if (position > 0) {
                                investmentPositions.add(InvestmentPosition.builder()
                                        .financialAsset(financialAsset.get())
                                        .positionDate(statementDate)
                                        .positionValue(position)
                                        .build());
                            }
                        } catch (ParseException e) {
                            ExceptionUtil.logErrorAndThrowException("Unable to parse position. nameInStatement: " + nameInStatement, e);
                        }

                        financialAsset.set(null);
                    }
                });

        log.info("{} investment funds from Banco do Brasil found.", investmentPositions.size());

        return investmentPositions;
    }
}