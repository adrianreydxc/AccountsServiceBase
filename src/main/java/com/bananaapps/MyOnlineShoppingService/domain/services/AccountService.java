package com.bananaapps.MyOnlineShoppingService.domain.services;

import com.bananaapps.MyOnlineShoppingService.domain.dto.request.AddMoneyDto;
import com.bananaapps.MyOnlineShoppingService.domain.dto.request.WithdrawDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAcounts() throws Exception;
    Account getAccountById(Long id) throws Exception;
    List<Account> getAccountsByUser(Long userId) throws Exception;
    boolean createAccount(Account account);
    boolean updateAccount(Account account);
    boolean deleteAccount(Account account);
    boolean doWithdraw(WithdrawDto withdrawDto) throws Exception;
    boolean deleteAllAccountByUser(Long userId);
    boolean addMoney(AddMoneyDto request);
}
