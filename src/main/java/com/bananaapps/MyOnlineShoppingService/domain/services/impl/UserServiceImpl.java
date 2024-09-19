package com.bananaapps.MyOnlineShoppingService.domain.services.impl;

import com.bananaapps.MyOnlineShoppingService.domain.dto.request.LoginDto;
import com.bananaapps.MyOnlineShoppingService.domain.dto.response.TokenDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.User;
import com.bananaapps.MyOnlineShoppingService.domain.exception.custom.LoginErrorException;
import com.bananaapps.MyOnlineShoppingService.domain.jwt.JwtUtils;
import com.bananaapps.MyOnlineShoppingService.domain.repositories.UserRepository;
import com.bananaapps.MyOnlineShoppingService.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    @Override
    public TokenDto login(LoginDto loginDto) {
        User optionalUser = userRepository.getUserByEmail(loginDto.getUsername(), loginDto.getPassword()).orElseThrow(()
                -> new LoginErrorException("No se ha encontrado usuario " + loginDto.getUsername() + " con el password " + loginDto.getPassword()));

                return new TokenDto(jwtUtils.generateToken(optionalUser.getUsername()));


//        return " {\"token\" : \"" + jwtUtils.generateToken(optionalUser.getUsername()) + "\"} ";
    }
}
