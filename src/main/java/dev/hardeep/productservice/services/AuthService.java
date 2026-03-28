package dev.hardeep.productservice.services;

import dev.hardeep.productservice.dtos.LoginRequestDto;
import dev.hardeep.productservice.dtos.RegisterRequestDto;
import dev.hardeep.productservice.models.User;

public interface AuthService {

    User register(RegisterRequestDto requestDto);
    User login(LoginRequestDto requestDto);
}
