package org.luckydime.api.financialasset;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FinancialAssetMapper {

    @Mapping(target = "investmentCompanyId", source = "investmentCompany.id")
    @Mapping(target = "assetCategoryId", source = "assetCategory.id")
    FinancialAssetDto map(FinancialAsset source);

    @InheritInverseConfiguration
    FinancialAsset map(FinancialAssetDto source);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<FinancialAssetDto> map(List<FinancialAsset> source);
}