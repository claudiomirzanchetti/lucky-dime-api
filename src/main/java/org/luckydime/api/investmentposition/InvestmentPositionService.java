package org.luckydime.api.investmentposition;

import lombok.RequiredArgsConstructor;
import org.luckydime.api.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentPositionService {
    private final InvestmentPositionRepository investmentPositionRepository;
    private final InvestmentPositionMapper investmentPositionMapper;

    public List<InvestmentPositionDto> findAll() {
        return investmentPositionMapper.map(investmentPositionRepository.findAll());
    }

    public InvestmentPositionDto findById(Long id) {
        return investmentPositionMapper.map(investmentPositionRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    public void saveAll(List<InvestmentPosition> investimentosBb) {
        investmentPositionRepository.saveAll(investimentosBb);
    }
}