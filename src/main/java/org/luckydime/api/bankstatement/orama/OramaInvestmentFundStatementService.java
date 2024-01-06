package org.luckydime.api.bankstatement.orama;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.luckydime.api.financialasset.FinancialAssetService;
import org.luckydime.api.investmentposition.InvestmentPosition;
import org.luckydime.api.util.CsvUtil;
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
public class OramaInvestmentFundStatementService {
    private final FinancialAssetService financialAssetService;

    public List<InvestmentPosition> getInvestmentPositions(LocalDate statementDate) {
        log.info("Getting investment positions from Orama investment fund statement.");

        var investmentPositions = new ArrayList<InvestmentPosition>();
        File investmentFundsFile = FileUtil.getStatementFile("orama-fi");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));

        CsvUtil.getLinesFromCsv(investmentFundsFile)
                .forEach(l -> {
                    String[] lineValues = CsvUtil.getValuesFromLine(l);

                    if (lineValues.length < 4) {
                        return;
                    }

                    var nameInStatement = lineValues[0];

                    if (isEmpty(nameInStatement)) {
                        return;
                    }

                    financialAssetService.findByNameInStatementOrderByNameAndOrderPresentInTheStatement(nameInStatement).stream()
                            .findFirst().ifPresent(financialAsset -> {
                                try {
                                    var saldoBrutoOramaSimples = lineValues[4];
                                    double position = numberFormat.parse(saldoBrutoOramaSimples.trim()).doubleValue();

                                    if (position > 0) {
                                        investmentPositions.add(InvestmentPosition.builder()
                                                .financialAsset(financialAsset)
                                                .positionDate(statementDate)
                                                .positionValue(position)
                                                .build());
                                    }
                                } catch (ParseException e) {
                                    throw new RuntimeException("Unable to parse position. nameInStatement: " + nameInStatement, e);
                                }
                            });
                });

        log.info("{} investment positions found.", investmentPositions.size());

        return investmentPositions;
    }
}