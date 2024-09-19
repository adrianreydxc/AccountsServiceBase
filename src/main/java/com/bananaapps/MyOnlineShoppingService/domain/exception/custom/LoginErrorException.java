package com.bananaapps.MyOnlineShoppingService.domain.exception.custom;

public class LoginErrorException extends RuntimeException{
    public LoginErrorException(String message){ super(message); }
}
