package com.bananaapps.MyOnlineShoppingService.domain.services;

import com.bananaapps.MyOnlineShoppingService.domain.dto.request.LoginDto;
import com.bananaapps.MyOnlineShoppingService.domain.dto.response.TokenDto;

public interface UserService {
    TokenDto login(LoginDto loginDto);
}
