package org.luckydime.api.bankstatement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/bank-statements")
@RequiredArgsConstructor
public class BankStatementController {
    private final BankStatementService bankStatementService;

    @PostMapping("/import/{statementDate}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void importBankStatements(@PathVariable("statementDate") LocalDate statementDate) {
        bankStatementService.importBankStatements(statementDate);
    }
}