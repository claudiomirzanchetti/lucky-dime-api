package org.luckydime.api.bankstatement;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.luckydime.api.bankstatement.bb.BbInvestmentFundStatementService;
import org.luckydime.api.bankstatement.bb.BbLcaStatementService;
import org.luckydime.api.bankstatement.orama.OramaFixedIncomeFundStatementService;
import org.luckydime.api.bankstatement.orama.OramaInvestmentFundStatementService;
import org.luckydime.api.bankstatement.orama.OramaVariableIncomeStatementService;
import org.luckydime.api.investmentposition.InvestmentPosition;
import org.luckydime.api.investmentposition.InvestmentPositionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BankStatementService {
    private final BbLcaStatementService bbLcaStatementService;
    private final BbInvestmentFundStatementService bbInvestmentFundStatementService;
    private final OramaInvestmentFundStatementService oramaInvestmentFundStatementService;
    private final OramaFixedIncomeFundStatementService oramaFixedIncomeFundStatementService;
    private final OramaVariableIncomeStatementService oramaVariableIncomeStatementService;
    private final InvestmentPositionService investmentPositionService;

    @Transactional
    public void importBankStatements(LocalDate statementDate) {
        var investmentPositions = new ArrayList<InvestmentPosition>();

        investmentPositions.addAll(bbInvestmentFundStatementService.getInvestmentPositions(statementDate));

        investmentPositions.addAll(bbLcaStatementService.getInvestmentPositions(statementDate));

        investmentPositions.addAll(oramaInvestmentFundStatementService.getInvestmentPositions(statementDate));

        investmentPositions.addAll(oramaFixedIncomeFundStatementService.getInvestmentPositions(statementDate));

        investmentPositions.addAll(oramaVariableIncomeStatementService.getInvestmentPositions(statementDate));

        investmentPositionService.saveAll(investmentPositions);
    }
}