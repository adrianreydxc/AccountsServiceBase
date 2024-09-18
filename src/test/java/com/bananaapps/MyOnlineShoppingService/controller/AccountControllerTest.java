package com.bananaapps.MyOnlineShoppingService.controller;

import com.bananaapps.MyOnlineShoppingService.domain.controllers.AccountController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles("testing")
@Sql(value = "classpath:data.sql")
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @BeforeEach
    void setup(){

    }

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
