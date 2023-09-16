package org.luckydime.api.assetcategory;

import lombok.RequiredArgsConstructor;
import org.luckydime.api.investmentcompany.InvestmentCompanyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lucky-dime-api/asset-categories")
@RequiredArgsConstructor
public class AssetCategoryController {
    private final AssetCategoryService assetCategoryService;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(assetCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetCategoryDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(assetCategoryService.findById(id));
    }
}
