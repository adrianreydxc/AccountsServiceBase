package com.bananaapps.MyOnlineShoppingService.domain.controllers;

import com.bananaapps.MyOnlineShoppingService.domain.dto.request.LoginDto;
import com.bananaapps.MyOnlineShoppingService.domain.dto.response.TokenDto;
import com.bananaapps.MyOnlineShoppingService.domain.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> doLogin(@RequestBody LoginDto request){
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }
}
