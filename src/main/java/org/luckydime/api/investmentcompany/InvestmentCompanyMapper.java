package org.luckydime.api.investmentcompany;

import org.mapstruct.Builder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InvestmentCompanyMapper {
    InvestmentCompanyDto map(InvestmentCompany source);

    @InheritInverseConfiguration
    InvestmentCompany map(InvestmentCompanyDto source);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<InvestmentCompanyDto> map(List<InvestmentCompany> source);
}
