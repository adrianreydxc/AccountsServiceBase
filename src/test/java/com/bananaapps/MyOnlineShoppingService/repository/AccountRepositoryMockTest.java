package com.bananaapps.MyOnlineShoppingService.repository;

import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Customer;
import com.bananaapps.MyOnlineShoppingService.domain.exception.custom.AccountsByUserException;
import com.bananaapps.MyOnlineShoppingService.domain.repositories.AccountServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.Matchers.*;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountRepositoryMockTest {

    @Mock
    private AccountServiceRepository accountServiceRepository;

    @BeforeEach
    void setUp(){
        List<Account> accounts = List.of(new Account(1L,"Type", LocalDate.MIN,1000, new Customer()));
        Mockito.when(accountServiceRepository.getAccountsByUser(1L)).thenReturn(Optional.of(accounts));
        Mockito.when(accountServiceRepository.getAccountsByUser(1000L)).thenReturn(Optional.of(List.of()));
        Mockito.when(accountServiceRepository.save(Mockito.any(Account.class))).
                thenAnswer(elem -> {
                    Account ac = (Account) elem.getArguments()[0];
                    ac.setId(-1L);
                    return ac;
                });
        Mockito.when(accountServiceRepository.findById(Mockito.any(Long.class))).thenAnswer(
                elem -> {
                    Long id = (Long) elem.getArguments()[0];
                    if (id > 999) return Optional.empty();
                    return Optional.of(new Account(id, "Type", LocalDate.MIN,1000, new Customer()));
                }
        );
    }

    @Test
    void getAccountByUser_AllOk(){
        List<Account> accounts = accountServiceRepository.getAccountsByUser(1L).orElseThrow(
                () -> new AccountsByUserException("This exception shouldn't be visible")
        );
        assertThat(accounts.size(), equalTo(1));
    }


    @Test
    void getAccountByUser_WrongId(){
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
