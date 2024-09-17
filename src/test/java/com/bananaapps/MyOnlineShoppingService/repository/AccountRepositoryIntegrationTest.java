package com.bananaapps.MyOnlineShoppingService.repository;

import com.bananaapps.MyOnlineShoppingService.domain.repositories.AccountServiceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountRepositoryIntegrationTest {

    @Autowired
    private AccountServiceRepository accountServiceRepository;

    @Test
    void getAccountByUser_AllOk(){

    }

    @Test
    void getAccountByUser_WrongId(){

    }

    @Test
    void deleteAllAccountsByUser_AllOk(){

    }

    @Test
    void deleteAllAccountsByUser_WrongId(){

    }
}
