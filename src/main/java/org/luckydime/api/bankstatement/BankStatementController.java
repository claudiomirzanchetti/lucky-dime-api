package org.luckydime.api.bankstatement;

import lombok.RequiredArgsConstructor;
import org.luckydime.api.investmentposition.InvestmentPositionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bank-statements")
@RequiredArgsConstructor
public class BankStatementController {
    private final BankStatementService bankStatementService;

    @PostMapping("/import/{statementDate}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<InvestmentPositionDto>> importBankStatements(@PathVariable("statementDate") LocalDate statementDate) {
        return ResponseEntity.ok(bankStatementService.importBankStatements(statementDate));
    }
}