package org.luckydime.api.investmentposition;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.luckydime.api.financialasset.FinancialAsset;

import java.time.LocalDate;

@Entity
@Table(name = "investment_position")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investment_position_seq")
    @SequenceGenerator(name="investment_position_seq", sequenceName = "investment_position_seq", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_asset_id")
    private FinancialAsset financialAsset;

    @Temporal(TemporalType.DATE)
    @Column(name = "position_date")
    private LocalDate positionDate;

    @Column(name = "position_value")
    private Double positionValue;
}