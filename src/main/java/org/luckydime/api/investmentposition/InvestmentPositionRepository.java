package org.luckydime.api.investmentposition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentPositionRepository extends JpaRepository<InvestmentPosition, Long> {
}