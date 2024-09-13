package com.bananaapps.MyOnlineShoppingService.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private String type;
    private LocalDate openingDate;
    private double balance;
    private CustomerDto owner;

}
