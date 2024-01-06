package org.luckydime.api.bankstatement.orama;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.luckydime.api.financialasset.FinancialAssetService;
import org.luckydime.api.investmentposition.InvestmentPosition;
import org.luckydime.api.util.CsvUtil;
import org.luckydime.api.util.ExceptionUtil;
import org.luckydime.api.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class OramaVariableIncomeStatementService {
    private final FinancialAssetService financialAssetService;

    public List<InvestmentPosition> getInvestmentPositions(LocalDate statementDate) {
        log.info("Getting investment positions from Orama variable income fund statement.");

        var investmentPositions = new ArrayList<InvestmentPosition>();
        File variableIncomeFile = FileUtil.getStatementFile("orama-rv");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));

        CsvUtil.getLinesFromCsv(variableIncomeFile)
                .forEach(l -> {
                    String[] lineValues = CsvUtil.getValuesFromLine(l);

                    var tickerSymbol = lineValues[0].split(StringUtils.SPACE)[0];

                    if (isEmpty(tickerSymbol) || tickerSymbol.contains("Código")) {
                        return;
                    }

                    financialAssetService.findByTickerSymbol(tickerSymbol)
                            .ifPresentOrElse(financialAsset -> {
                                        try {
                                            var numberOfShares = numberFormat.parse(lineValues[3]).intValue();
                                            var sharePrice = numberFormat.parse(lineValues[4].trim()).doubleValue();
                                            double position = numberFormat.parse(lineValues[7].trim()).doubleValue();

                                            if (numberOfShares > 0) {
                                                investmentPositions.add(InvestmentPosition.builder()
                                                        .financialAsset(financialAsset)
                                                        .positionDate(statementDate)
                                                        .numberOfShares(numberOfShares)
                                                        .sharePrice(sharePrice)
                                                        .positionValue(position)
                                                        .build());
                                            } else {
                                                log.warn("The tickerSymbol {} was found but has zero shares.", tickerSymbol);
                                            }
                                        } catch (ParseException e) {
                                            ExceptionUtil.logErrorAndThrowException("Unable to parse position. tickerSymbol: " + tickerSymbol, e);
                                        }
                                    },
                                    () -> log.warn("Financial Asset not found. tickerSymbol: " + tickerSymbol)
                            );
                });

        log.info("{} investment positions found.", investmentPositions.size());

        return investmentPositions;
    }
}