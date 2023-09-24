package org.luckydime.api.investmentcompany;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "investment_company")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investment_company_seq")
    @SequenceGenerator(name="investment_company_seq", sequenceName = "investment_company_seq", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column
    private String name;
}