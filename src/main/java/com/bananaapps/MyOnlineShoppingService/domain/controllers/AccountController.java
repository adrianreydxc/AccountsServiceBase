package com.bananaapps.MyOnlineShoppingService.domain.controllers;

import com.bananaapps.MyOnlineShoppingService.domain.dto.request.MoneyTransactionsDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import com.bananaapps.MyOnlineShoppingService.domain.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping()
    public List<Account> getAccounts() throws Exception {
        return accountService.getAllAcounts();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable("id") Long id) throws Exception {
        return accountService.getAccountById(id);
    }

    @GetMapping("user/{id}")
    public List<Account> getAccountByUserId(@PathVariable("id") Long id) throws Exception {
        return accountService.getAccountsByUser(id);
    }
    @PostMapping("/create")
    public boolean createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/update")
    public boolean updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/delete")
    public boolean deleteAccount(@RequestBody Account account) {
        return accountService.deleteAccount(account);
    }

    @PutMapping("/addMoney")
    public boolean addMoney(@RequestBody MoneyTransactionsDto request) throws Exception {
        return accountService.addMoney(request);
    }

    @PutMapping("/withdrawMoney")
    public boolean withdrawMoney(@RequestBody MoneyTransactionsDto request) throws Exception {
        return accountService.doWithdraw(request);
    }

    @DeleteMapping("/deleteAllFromUser/{id}")
    public boolean deleteAllFromUser(@PathVariable("id") Long id) throws Exception {
        return accountService.deleteAllAccountByUser(id);
    }
}
