package org.luckydime.api.investmentcompany;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @GeneratedValue
    private Long id;

    @Column
    private String name;
}
