package com.bananaapps.MyOnlineShoppingService.domain.services;

import com.bananaapps.MyOnlineShoppingService.domain.dto.request.LoanDto;
import com.bananaapps.MyOnlineShoppingService.domain.dto.request.MoneyTransactionsDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAcounts() throws Exception;
    Account getAccountById(Long id, Long userId) throws Exception;
    List<Account> getAccountsByUser(Long userId) throws Exception;
    boolean createAccount(Account account);
    boolean updateAccount(Account account);
    boolean deleteAccount(Account account);
    boolean doWithdraw(MoneyTransactionsDto withdrawDto) throws Exception;
    boolean deleteAllAccountByUser(Long userId);
    boolean addMoney(MoneyTransactionsDto request);
    boolean checkLoan(LoanDto loanDto);
}
