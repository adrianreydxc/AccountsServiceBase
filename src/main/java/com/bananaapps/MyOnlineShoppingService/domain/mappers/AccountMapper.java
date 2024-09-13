package com.bananaapps.MyOnlineShoppingService.domain.mappers;

import com.bananaapps.MyOnlineShoppingService.domain.dto.response.AccountDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountDto toDto(Account account) {

        if(account == null) {
            return null;
        }

        return AccountDto.builder()
                .id(account.getId())
                .type(account.getType())
                .openingDate(account.getOpeningDate())
                .owner(CustomerMapper.toDto(account.getOwner()))
                .balance(account.getBalance())
                .build();
    }

    public static Account toEntity(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }

        Account account = new Account();
        account.setId(accountDto.getId());
        account.setType(accountDto.getType());
        account.setOpeningDate(accountDto.getOpeningDate());
        account.setOwner(CustomerMapper.toEntity(accountDto.getOwner())); // Asume que tienes un CustomerMapper
        account.setBalance(accountDto.getBalance());
        return account;
    }

}
