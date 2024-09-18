package com.bananaapps.MyOnlineShoppingService.controller;


import com.bananaapps.MyOnlineShoppingService.domain.dto.request.MoneyTransactionsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("classpath:data.sql")
public class AccountControllerTestRestTemplate {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void givenValidRequest_whenAddMoney_thenReturnCreatedAndTrue() {
        // Given
        MoneyTransactionsDto request = new MoneyTransactionsDto();
        request.setIdUser(3L);
        request.setIdAccount(1L);
        request.setAmount(100.0);

        String url = "http://localhost:" + port + "/accounts/addMoney";

        HttpEntity<MoneyTransactionsDto> entity = new HttpEntity<>(request);

        // When
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Boolean.class);

        // Then
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
        assertThat(response.getBody(), is(true));
    }


}
