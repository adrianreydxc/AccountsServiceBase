package com.bananaapps.MyOnlineShoppingService.domain.services.impl;

import com.bananaapps.MyOnlineShoppingService.domain.dto.request.LoanDto;
import com.bananaapps.MyOnlineShoppingService.domain.dto.request.MoneyTransactionsDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import com.bananaapps.MyOnlineShoppingService.domain.repositories.AccountServiceRepository;
import com.bananaapps.MyOnlineShoppingService.domain.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Autowired
    private final AccountServiceRepository accountServiceRepository;
    @Override
    public List<Account> getAllAcounts() throws Exception {
        try{
            return accountServiceRepository.findAll();

        }catch (Exception e){
            throw new Exception("Error al optener todas las cuentas");
        }
    }

    @Override
    public Account getAccountById(Long id) throws Exception {

        try{
            return accountServiceRepository.findById(id).orElseThrow();
        }catch (Exception e){
            throw new Exception("Error al obtener la cuenta con id: " + id);
        }
    }

    @Override
    public List<Account> getAccountsByUser(Long userId) throws Exception {
        try{
            return accountServiceRepository.getAccountsByUser(userId).orElseThrow();
        }catch (Exception e){
            throw new Exception("");
        }
    }

    @Override
    public boolean createAccount(Account account) {
        try{
            accountServiceRepository.save(account);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateAccount(Account account) {
          try{
            accountServiceRepository.save(account);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteAccount(Account account) {
          try{
            accountServiceRepository.delete(account);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean doWithdraw(MoneyTransactionsDto withdrawDto) throws Exception {
     try{
            Account account = accountServiceRepository.findById(withdrawDto.getIdAccount()).orElseThrow();
        if(account.getBalance() < withdrawDto.getAmount()){
            return false;
        }
        account.setBalance(account.getBalance() - withdrawDto.getAmount());
        accountServiceRepository.save(account);
        return true;
     }catch (Exception e){
         return false;
     }
    }

    @Override
    public boolean deleteAllAccountByUser(Long userId) {
        try{
            accountServiceRepository.deleteAllAccountsByUser(userId);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean addMoney(MoneyTransactionsDto request) {
       try{
           Account account = accountServiceRepository.findById(request.getIdAccount()).orElseThrow();
           account.setBalance(account.getBalance() + request.getAmount());

           accountServiceRepository.save(account);
           return true;
       }catch (Exception e){
           return false;
       }
    }

    @Override
    public boolean checkLoan(LoanDto request) {
       List<Account> accounts = accountServiceRepository.getAccountsByUser(request.getId()).orElseThrow();
        double totalAmount = 0;
        for(Account u : accounts){
            totalAmount += u.getBalance();
        }
        double finalAmount = totalAmount * 0.8;
        if(request.getAmount() < finalAmount){
           return true;
        }else{
            return false;
        }
    }
}
