package org.luckydime.api.investmentposition;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/investment-positions")
@RequiredArgsConstructor
public class InvestmentPositionController {
    private final InvestmentPositionService investmentPositionService;

    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(investmentPositionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentPositionDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(investmentPositionService.findById(id));
    }

    @GetMapping
    public ResponseEntity getPositionsByYearMonth(@RequestParam("yearMonth") String yearMonth) {
        return ResponseEntity.ok(investmentPositionService.getPositionsByYearMonth(yearMonth));
    }
}