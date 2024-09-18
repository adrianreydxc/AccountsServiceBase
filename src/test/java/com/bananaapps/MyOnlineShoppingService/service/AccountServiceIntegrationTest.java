package com.bananaapps.MyOnlineShoppingService.service;

import com.bananaapps.MyOnlineShoppingService.domain.dto.response.AccountDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import com.bananaapps.MyOnlineShoppingService.domain.exception.custom.AccountsByUserException;
import com.bananaapps.MyOnlineShoppingService.domain.exception.custom.NoSuchAccountException;
import com.bananaapps.MyOnlineShoppingService.domain.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class AccountServiceIntegrationTest {

    @Autowired
    private AccountService accountService;

    @Test
    void getAllAccounts_OK() throws Exception {
        List<AccountDto> list = accountService.getAllAcounts();
        assertThat(list).isNotNull();
        assertThat(list.size()).isGreaterThan(0);
        assertThat(list.get(0).getBalance()).isEqualTo(1000);
        assertThat(list.get(0).getType()).isEqualTo("Personal");
        assertThat(list.get(0).getOwner_id()).isEqualTo(1L);
    }

    @Test
    void getAccountById_AllOk() throws Exception {
        AccountDto account = accountService.getAccountById(1L, 1L);
        assertThat(account).isNotNull();
        assertThat(account.getBalance()).isEqualTo(1000);
        assertThat(account.getType()).isEqualTo("Personal");
        assertThat(account.getOwner_id()).isEqualTo(1L);
    }

    @Test
    void getAccountById_NoExistId() throws Exception {
        assertThrows(NoSuchAccountException.class, () -> {
            accountService.getAccountById(23232L, 1L);
        });
    }

    @Test
    void getAccountById_WrongUserId(){
        assertThrows(NoSuchAccountException.class, () -> {
            accountService.getAccountById( 1L, 342342343L);
        });
    }

    @Test
    void getAccountsByUser_AllOK() throws Exception {
        List<AccountDto> accounts = accountService.getAccountsByUser(1L);
        assertThat(accounts).isNotNull();
        assertThat(accounts.size()).isGreaterThan(0);
        assertThat(accounts.get(0).getBalance()).isEqualTo(1000);
        assertThat(accounts.get(0).getType()).isEqualTo("Personal");
        assertThat(accounts.get(0).getOwner_id()).isEqualTo(1L);
    }

    @Test
    void getAccountsByUser_WrongUserId() throws Exception {
      List<AccountDto> list = accountService.getAccountsByUser(23423432432L);
      assertThat(list.size()).isEqualTo(0);
    }
}
