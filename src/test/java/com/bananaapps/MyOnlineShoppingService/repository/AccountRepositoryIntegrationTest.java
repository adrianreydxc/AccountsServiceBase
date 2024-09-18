package com.bananaapps.MyOnlineShoppingService.repository;

import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import com.bananaapps.MyOnlineShoppingService.domain.exception.custom.AccountsByUserException;
import com.bananaapps.MyOnlineShoppingService.domain.repositories.AccountServiceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AccountRepositoryIntegrationTest {

    @Autowired
    private AccountServiceRepository accountServiceRepository;

    @Test
    void getAccountsByUser_AllOk() {
        List<Account> accounts = accountServiceRepository.getAccountsByUser(1L).orElseThrow(
                () -> new AccountsByUserException("This exception shouldn't be visible")
        );
        assertThat(accounts.size(), greaterThan(0));
    }

    @Test
    void getAccountByUser_WrongId() {
        List<Account> accounts = accountServiceRepository.getAccountsByUser(1000L).orElseThrow(
                () -> new AccountsByUserException("This exception shouldn't be visible")
        );
        assertThat(accounts.size(), equalTo(0));
    }

    @Test
    void findAccount_AllOk() {
        Account account = accountServiceRepository.findById(1L).orElseThrow(
                () -> new AccountsByUserException("Usuario no encontrado")
        );
        assertThat(account, notNullValue());

    }

    @Test
    void findAccount_WrongId() {
        assertThrows(AccountsByUserException.class, () -> accountServiceRepository.findById(1000L).orElseThrow(
                () -> new AccountsByUserException("Usuario no encontrado")
        ));
    }
}
