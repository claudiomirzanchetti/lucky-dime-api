package org.luckydime.api.bankstatement.orama;

import lombok.RequiredArgsConstructor;
import org.luckydime.api.financialasset.FinancialAsset;
import org.luckydime.api.financialasset.FinancialAssetService;
import org.luckydime.api.investmentposition.InvestmentPosition;
import org.luckydime.api.util.CsvUtils;
import org.luckydime.api.util.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class OramaFixedIncomeFundStatementService {
    private final FinancialAssetService financialAssetService;

    public List<InvestmentPosition> getInvestmentPositions(LocalDate statementDate) {
        var investmentFunds = new ArrayList<InvestmentPosition>();
        File investmentFundsFile = FileUtils.getStatementFile("orama-rf");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        AtomicInteger purchaseNumber = new AtomicInteger(0);
        AtomicReference<List<FinancialAsset>> assetsWithNameInStatement = new AtomicReference<>(new ArrayList<>());

        CsvUtils.getLinesFromCsv(investmentFundsFile)
                .forEach(l -> {
                    String[] lineValues = CsvUtils.getValuesFromLine(l);

                    if (lineValues.length < 4) {
                        return;
                    }

                    var firstToken = lineValues[0];

                    if (isEmpty(firstToken)) {
                        return;
                    }

                    if (financialAssetService.isNameInStatementExistent(firstToken)) {
                        assetsWithNameInStatement.set(financialAssetService.findByNameInStatementOrderByNameAndOrderPresentInTheStatement(firstToken));
                        purchaseNumber.set(0);
                        return;
                    }

                    if (firstToken.contains("Compra")) {
                        try {
                            FinancialAsset currentAsset = assetsWithNameInStatement.get().get(purchaseNumber.getAndIncrement());

                            double position = numberFormat.parse(lineValues[5].trim()).doubleValue();

                            if (position > 0) {
                                investmentFunds.add(InvestmentPosition.builder()
                                        .financialAsset(currentAsset)
                                        .positionDate(statementDate)
                                        .positionValue(position)
                                        .build());
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

        return investmentFunds;
    }
}