package org.luckydime.api.financialasset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialAssetRepository extends JpaRepository<FinancialAsset, Long> {
    List<FinancialAsset> findByNameInStatementNotNullAndInvestmentCompanyIdOrderByNameInStatementAscOrderInStatementAsc(Long investmentCompanyId);

    List<FinancialAsset> findByNameInStatementOrderByNameInStatementAscOrderInStatementAsc(String nameInStatement);

    Optional<FinancialAsset> findByTickerSymbol(String tickerSymbol);
}