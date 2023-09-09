package com.luckydime.api.assetcategory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetCategoryService {
    private final AssetCategoryRepository assetCategoryRepository;

    public List<AssetCategory> findAll() {
        return assetCategoryRepository.findAll();
    }
}
