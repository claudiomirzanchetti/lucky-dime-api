package org.luckydime.api.investmentposition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvestmentPositionRepository extends JpaRepository<InvestmentPosition, Long> {
    List<InvestmentPosition> findByPositionDateBetween(LocalDate startPositionDate, LocalDate endPositionDate);
}