package com.bananaapps.MyOnlineShoppingService.domain.exception.custom;

public class MethodNotValidException extends RuntimeException{
    public MethodNotValidException(String message){
        super(message);
    }
}
