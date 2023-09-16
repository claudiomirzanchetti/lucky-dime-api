package org.luckydime.api.investmentcompany;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lucky-dime-api/investment-companies")
@RequiredArgsConstructor
public class InvestmentCompanyController {
    private final InvestmentCompanyService investmentCompanyService;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(investmentCompanyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentCompanyDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(investmentCompanyService.findById(id));
    }
}
