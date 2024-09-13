package com.bananaapps.MyOnlineShoppingService.domain.mappers;

import com.bananaapps.MyOnlineShoppingService.domain.dto.response.AccountDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public static AccountDto toDto (Account account) {

        if(account == null) {
            return null;
        }

        return AccountDto.builder()
                .id(account.getId())
                .type(account.getType())
                .openingDate(account.getOpeningDate())
                .balance(account.getBalance())
                .build();
    }

}
