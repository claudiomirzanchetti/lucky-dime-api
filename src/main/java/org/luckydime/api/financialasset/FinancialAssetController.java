package org.luckydime.api.financialasset;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financial-assets")
@RequiredArgsConstructor
public class FinancialAssetController {
    private final FinancialAssetService financialAssetService;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(financialAssetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialAssetDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(financialAssetService.findById(id));
    }

    @PostMapping
    public ResponseEntity<FinancialAssetDto> create(@RequestBody FinancialAssetDto financialAssetDto) {
        return new ResponseEntity<>(financialAssetService.save(financialAssetDto), HttpStatus.CREATED);
    }
}
