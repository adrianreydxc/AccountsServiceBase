package com.bananaapps.MyOnlineShoppingService.service;

import com.bananaapps.MyOnlineShoppingService.domain.dto.response.AccountDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import com.bananaapps.MyOnlineShoppingService.domain.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AccountServiceIntegrationTest {

    @Autowired
    private AccountService accountService;

    @Test
    void getAllAccounts_OK() throws Exception {
        List<AccountDto> list = accountService.getAllAcounts();

    }

    @Test
    void getAccountById_AllOk(){

    }

    @Test
    void getAccountById_NoExistId(){

    }

    @Test
    void getAccountById_WrongUserId(){

    }

    @Test
    void getAccountsByUser_AllOK(){

    }

    @Test
    void getAccountsByUser_WrongUserId(){

    }

    @Test
    void createAccount_AllOk(){

    }

    @Test
    void createAccount_WrongValidate(){

    }
}
