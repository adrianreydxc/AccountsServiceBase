package com.bananaapps.MyOnlineShoppingService.domain.controllers;

import com.bananaapps.MyOnlineShoppingService.domain.dto.request.LoanDto;
import com.bananaapps.MyOnlineShoppingService.domain.dto.request.MoneyTransactionsDto;
import com.bananaapps.MyOnlineShoppingService.domain.dto.response.AccountDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import com.bananaapps.MyOnlineShoppingService.domain.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAccounts() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAcounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountById(id));
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<AccountDto>> getAccountByUserId(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountsByUser(id));
    }
    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(account));
    }

    @PutMapping()
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.updateAccount(account));
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> deleteAccount(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(accountService.deleteAccount(account));
    }

    @PutMapping("/addMoney")
    public ResponseEntity<Boolean> addMoney(@RequestBody MoneyTransactionsDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.addMoney(request));
    }

    @PutMapping("/withdrawMoney")
    public ResponseEntity<Boolean> withdrawMoney(@RequestBody MoneyTransactionsDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.doWithdraw(request));
    }

    @DeleteMapping("/deleteAllFromUser/{id}")
    public ResponseEntity<Boolean> deleteAllFromUser(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(accountService.deleteAllAccountByUser(id));
    }

    @PostMapping("/loan/{userId}")
    public ResponseEntity<Boolean> isLoanAccepted(@PathVariable("userId") Long userId, @RequestBody LoanDto body) {
        body.setId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(accountService.checkLoan(body));
    }

}
