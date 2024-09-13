package com.bananaapps.MyOnlineShoppingService.domain.services.impl;

import com.bananaapps.MyOnlineShoppingService.domain.dto.request.LoanDto;
import com.bananaapps.MyOnlineShoppingService.domain.dto.request.MoneyTransactionsDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import com.bananaapps.MyOnlineShoppingService.domain.exception.custom.*;
import com.bananaapps.MyOnlineShoppingService.domain.repositories.AccountServiceRepository;
import com.bananaapps.MyOnlineShoppingService.domain.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Autowired
    private final AccountServiceRepository accountServiceRepository;
    @Override
    public List<Account> getAllAcounts(){
        try{
            List<Account> list = accountServiceRepository.findAll();
            if (list.isEmpty()) {
                throw new NoSuchAccountsException("No hay ninguna almacenda en la base de datos");
            }
            return list;
        }catch (Exception e){
            throw new NoSuchAccountsException("Error al optener todas las cuentas");
        }
    }

    @Override
    public Account getAccountById(Long id) {
        return accountServiceRepository.findById(id).orElseThrow(()
                -> new NoSuchAccountException("Error al obtener la cuenta con id: " + id));
    }

    @Override
    public List<Account> getAccountsByUser(Long userId) {
        return accountServiceRepository.getAccountsByUser(userId).orElseThrow(()
                -> new AccountsByUserException("El usuario con id: " + userId + " no tiene ninguna account en base de datos"));
    }

    @Override
    public boolean createAccount(Account account) {
        try{
            accountServiceRepository.save(account);

            return true;
        }catch (NewAccountException e){
            throw new NewAccountException("Error al crear account");
           // return false;
        }
    }

    @Override
    public boolean updateAccount(Account account) {
          try{
            accountServiceRepository.save(account);

            return true;
        }catch (Exception e){
              throw new NewAccountException("Error al actualizar account");
            //return false;
        }
    }

    @Override
    public boolean deleteAccount(Account account) {
          try{
            accountServiceRepository.delete(account);

            return true;
        }catch (Exception e){
            throw new NewAccountException("Error al eliminar account");
//            return false;
        }
    }

    @Override
    public boolean doWithdraw(MoneyTransactionsDto withdrawDto) {
     try{
         Account account = accountServiceRepository.findById(withdrawDto.getIdAccount()).orElseThrow(()
            -> new WithDrawnException("No se ha encontrado account en la base de datos con id: " + withdrawDto.getIdAccount()));
        if(account.getBalance() < withdrawDto.getAmount()){
            throw new WithDrawnException("Balance insuficiente para hacer retiro");
            //return false;
        }
        account.setBalance(account.getBalance() - withdrawDto.getAmount());
        accountServiceRepository.save(account);
        return true;
     }catch (Exception e){
         throw new WithDrawnException("Error al hacer retiro de dinero");
         //return false;
     }
    }

    @Override
    public boolean deleteAllAccountByUser(Long userId) {
        try{
            accountServiceRepository.deleteAllAccountsByUser(userId);

            return true;
        }catch (Exception e){
            throw new DeleteAllAccountsException("Error al eliminar todas las accounts del usuario con id: " + userId);
            //return false;
        }
    }

    @Override
    public boolean addMoney(MoneyTransactionsDto request) {
       try{
           Account account = accountServiceRepository.findById(request.getIdAccount()).orElseThrow(()
                   -> new AddMoneyException("Error al obtner account con id: " + request.getIdAccount()));
           account.setBalance(account.getBalance() + request.getAmount());

           accountServiceRepository.save(account);
           return true;
       }catch (Exception e){
           throw new AddMoneyException("Error al agregar dinero a la account con id: " + request.getIdAccount());
           //return false;
       }
    }

    @Override
    public boolean checkLoan(LoanDto request) {
       List<Account> accounts = accountServiceRepository.getAccountsByUser(request.getId()).orElseThrow(()
                    -> new LoanException("No se han encontrado accounst en la base de datos"));
        double totalAmount = 0;

        for(Account u : accounts){
            totalAmount += u.getBalance();
        }

        double finalAmount = totalAmount * 0.8;

        if(request.getAmount() < finalAmount){
           return true;
        }else{
            throw new LoanException("El balance total insuficiente");
        }
    }
}
