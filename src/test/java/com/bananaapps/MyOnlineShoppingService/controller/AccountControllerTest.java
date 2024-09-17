package com.bananaapps.MyOnlineShoppingService.controller;

import com.bananaapps.MyOnlineShoppingService.domain.controllers.AccountController;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create_allOk(){

    }

    @Test
    void create_WrongValidation(){

    }

    @Test
    void getAccountById_AllOk(){

    }

    @Test
    void getAccountById_WrongId(){

    }

    @Test
    void deleteAccount_AllOK(){

    }

    @Test
    void deleteAccount_WrongId(){

    }
}
