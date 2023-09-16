package org.luckydime.api.investmentcompany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentCompanyRepository extends JpaRepository<InvestmentCompany, Long> {
}
