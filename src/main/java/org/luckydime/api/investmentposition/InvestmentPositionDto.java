package org.luckydime.api.investmentposition;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentPositionDto {
    private Long id;

    @NotNull
    private Long financialAssetId;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate positionDate;

    @DecimalMin(value = "0.01")
    private Double positionValue;

    public String getMonthYear() {
        return isNull(positionDate)
                ? StringUtils.EMPTY
                : positionDate.getYear() + "-" + String.format("%02d", positionDate.getMonth().getValue());
    }
}