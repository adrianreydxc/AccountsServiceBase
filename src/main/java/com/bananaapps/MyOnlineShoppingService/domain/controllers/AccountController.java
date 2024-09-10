package com.bananaapps.MyOnlineShoppingService.domain.controllers;

import com.bananaapps.MyOnlineShoppingService.domain.dto.request.WithdrawDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Customer;
import com.bananaapps.MyOnlineShoppingService.domain.repositories.AccountServiceRepository;
import com.bananaapps.MyOnlineShoppingService.domain.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @PostMapping("/create")
    public boolean createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/update")
    public boolean updateAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @DeleteMapping("/delete")
    public boolean deleteAccount(@RequestBody Account account) {
        return accountService.deleteAccount(account);
    }

    @PutMapping("/addMoney")
    public boolean addMoney(@RequestBody WithdrawDto request) throws Exception {
        return accountService.doWithdraw(request);
    }
}
