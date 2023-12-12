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
    InvestmentPositionDto map(InvestmentPosition source);

    @InheritInverseConfiguration
    InvestmentPosition map(InvestmentPositionDto source);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<InvestmentPositionDto> map(List<InvestmentPosition> source);
}