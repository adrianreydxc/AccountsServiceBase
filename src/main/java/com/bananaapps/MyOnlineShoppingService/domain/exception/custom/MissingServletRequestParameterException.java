package com.bananaapps.MyOnlineShoppingService.domain.exception.custom;

public class MissingServletRequestParameterException extends RuntimeException{
    public MissingServletRequestParameterException(String message){ super(message); }
}
