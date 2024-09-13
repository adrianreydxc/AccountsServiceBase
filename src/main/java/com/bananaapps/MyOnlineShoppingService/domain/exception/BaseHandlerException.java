package com.bananaapps.MyOnlineShoppingService.domain.exception;

import com.bananaapps.MyOnlineShoppingService.domain.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BaseHandlerException {

    @ExceptionHandler(AddMoneyException.class)
    public ResponseEntity AddMoneyHandlerException(AddMoneyException ex){
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DeleteAccountException.class)
    public ResponseEntity DeleteHandlerException(DeleteAccountException ex){
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DeleteAllAccountsException.class)
    public ResponseEntity DeleteAllHandlerException(DeleteAllAccountsException ex){
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoanException.class)
    public ResponseEntity LoanHandlerException(LoanException ex){
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NewAccountException.class)
    public ResponseEntity NewAccountException(NewAccountException ex){
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchAccountException.class)
    public ResponseEntity NoSuchAccountHandlerException(NoSuchAccountException ex){
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UpdateAccountException.class)
    public ResponseEntity UpdateHandlerException(UpdateAccountException ex){
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WithDrawnException.class)
    public ResponseEntity WithDrawnHandlerException(WithDrawnException ex){
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchCustomerException.class)
    public ResponseEntity CustomerHandlerException(NoSuchCustomerException ex){
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchAccountsException.class)
    public ResponseEntity AccountsHandlerException(NoSuchAccountsException ex){
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
}
