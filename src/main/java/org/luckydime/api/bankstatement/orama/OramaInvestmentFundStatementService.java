package org.luckydime.api.bankstatement.orama;

import lombok.RequiredArgsConstructor;
import org.luckydime.api.financialasset.FinancialAssetService;
import org.luckydime.api.investmentposition.InvestmentPosition;
import org.luckydime.api.util.CsvUtils;
import org.luckydime.api.util.FileUtils;
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
public class OramaInvestmentFundStatementService {
    private final FinancialAssetService financialAssetService;

    public List<InvestmentPosition> getInvestmentPositions(LocalDate statementDate) {
        var investmentFunds = new ArrayList<InvestmentPosition>();
        File investmentFundsFile = FileUtils.getStatementFile("orama-fi");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));

        CsvUtils.getLinesFromCsv(investmentFundsFile)
                .forEach(l -> {
                    String[] lineValues = CsvUtils.getValuesFromLine(l);

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
                });

        return investmentFunds;
    }
}