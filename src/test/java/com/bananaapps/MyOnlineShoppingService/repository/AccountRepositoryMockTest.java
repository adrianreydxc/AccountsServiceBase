package com.bananaapps.MyOnlineShoppingService.repository;

import com.bananaapps.MyOnlineShoppingService.domain.repositories.AccountServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountRepositoryMockTest {

    @Mock
    private AccountServiceRepository accountServiceRepository;

    @BeforeEach
    void setup(){

    }

    @Test
    void getAccountByUser_AllOk(){

    }

    @Test
    void getAccountByUser_WrongId(){

    }

    @Test
    void findAccount_AllOk(){

    }

    @Test
    void findAccount_WrongId(){

    }
}
