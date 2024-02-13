package org.luckydime.api.investmentposition;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InvestmentPositionMapper {

    @Mapping(target = "financialAssetId", source = "financialAsset.id")
    @Mapping(target = "financialAssetName", source = "financialAsset.name")
    @Mapping(target = "financialAssetTickerSymbol", source = "financialAsset.tickerSymbol")
    @Mapping(target = "financialAssetReference", source = "financialAsset.reference")
    @Mapping(target = "investmentCompanyName", source = "financialAsset.investmentCompany.name")
    @Mapping(target = "assetCategoryDescription", source = "financialAsset.assetCategory.description")
    InvestmentPositionDto map(InvestmentPosition source);

    @InheritInverseConfiguration
    InvestmentPosition map(InvestmentPositionDto source);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<InvestmentPositionDto> map(List<InvestmentPosition> source);
}