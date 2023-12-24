package org.luckydime.api.financialasset;

import lombok.RequiredArgsConstructor;
import org.luckydime.api.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinancialAssetService {
    private final FinancialAssetRepository financialAssetRepository;
    private final FinancialAssetMapper financialAssetMapper;

    public List<FinancialAssetDto> findAll() {
        return financialAssetMapper.map(financialAssetRepository.findAll());
    }

    public FinancialAssetDto findById(Long id) {
        return financialAssetMapper.map(financialAssetRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    public FinancialAssetDto save(FinancialAssetDto financialAssetDto) {
        return financialAssetMapper.map(financialAssetRepository.save(financialAssetMapper.map(financialAssetDto)));
    }

    public List<FinancialAsset> findByNameInStatementOrderByNameAndOrderPresentInTheStatement(String nameInStatement) {
        return financialAssetRepository.findByNameInStatementOrderByNameInStatementAscOrderInStatementAsc(nameInStatement);
    }

    public boolean isNameInStatementExistent(String nameInStatement) {
        return !financialAssetRepository.findByNameInStatementOrderByNameInStatementAscOrderInStatementAsc(nameInStatement).isEmpty();
    }

    public Optional<FinancialAsset> findByTickerSymbol(String tickerSymbol) {
        return financialAssetRepository.findByTickerSymbol(tickerSymbol);
    }
}