package org.luckydime.api.financialasset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialAssetRepository extends JpaRepository<FinancialAsset, Long> {
}