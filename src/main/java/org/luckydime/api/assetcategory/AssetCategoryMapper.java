package org.luckydime.api.assetcategory;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AssetCategoryMapper {
    AssetCategoryDto map(AssetCategory source);

    @InheritInverseConfiguration
    AssetCategory map(AssetCategoryDto source);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<AssetCategoryDto> map(List<AssetCategory> source);
}
