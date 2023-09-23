package org.luckydime.api.investmentcompany;

import lombok.RequiredArgsConstructor;
import org.luckydime.api.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentCompanyService {
    private final InvestmentCompanyRepository investmentCompanyRepository;
    private final InvestmentCompanyMapper investmentCompanyMapper;

    public List<InvestmentCompanyDto> findAll() {
        return investmentCompanyMapper.map(investmentCompanyRepository.findAll());
    }

    public InvestmentCompanyDto findById(Long id) {
        return investmentCompanyMapper.map(investmentCompanyRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }
}