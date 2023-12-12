package org.luckydime.api.financialasset;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinancialAssetDto {
    private Long id;

    @NotNull
    @Size(max = 200)
    private String name;

    @Size(max = 20)
    private String tickerSymbol;

    @NotNull
    private Long investmentCompanyId;

    @NotNull
    private Long assetCategoryId;

    @Builder.Default
    private Boolean active = Boolean.TRUE;

    @PositiveOrZero
    private String announcedReturn;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate maturityDate;

    @Size(max = 200)
    private String reference;

    @Size(max = 2000)
    private String note;

    @JsonIgnore
    private double totalAmount;

    @JsonIgnore
    private int numberOfShares;

    @JsonIgnore
    private double avarageStockPrice;

    @JsonIgnore
    private double currentStockPrice;

    @JsonIgnore
    private double priceVsAverageRatio;
}