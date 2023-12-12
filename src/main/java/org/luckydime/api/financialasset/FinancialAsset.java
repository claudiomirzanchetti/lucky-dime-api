package org.luckydime.api.financialasset;

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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.luckydime.api.assetcategory.AssetCategory;
import org.luckydime.api.investmentcompany.InvestmentCompany;

import java.time.LocalDate;

@Entity
@Table(name = "financial_asset")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinancialAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financial_asset_seq")
    @SequenceGenerator(name="financial_asset_seq", sequenceName = "financial_asset_seq", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ticker_symbol")
    private String tickerSymbol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investment_company_id")
    private InvestmentCompany investmentCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_category_id")
    private AssetCategory assetCategory;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "announced_return")
    private String announcedReturn;

    @Temporal(TemporalType.DATE)
    @Column(name = "maturity_date")
    private LocalDate maturityDate;

    @Column(name = "reference")
    private String reference;

    @Column(name = "note")
    private String note;

    @Transient
    private double totalAmount;

    @Transient
    private int numberOfShares;

    @Transient
    private double avarageStockPrice;

    @Transient
    private double currentStockPrice;

    @Transient
    private double priceVsAverageRatio;
}