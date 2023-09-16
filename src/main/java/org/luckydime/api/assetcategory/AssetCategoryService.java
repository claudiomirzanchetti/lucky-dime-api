package org.luckydime.api.assetcategory;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetCategoryService {
    private final AssetCategoryRepository assetCategoryRepository;
    private final AssetCategoryMapper assetCategoryMapper;

    public List<AssetCategoryDto> findAll() {
        return assetCategoryMapper.map(assetCategoryRepository.findAll());
    }

    public AssetCategoryDto findById(Long id) {
        return assetCategoryMapper.map(assetCategoryRepository.findById(id).orElse(null));
    }
}
