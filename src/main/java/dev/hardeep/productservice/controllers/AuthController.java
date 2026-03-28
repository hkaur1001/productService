package dev.hardeep.productservice.controllers;

import dev.hardeep.productservice.dtos.LoginRequestDto;
import dev.hardeep.productservice.dtos.RegisterRequestDto;
import dev.hardeep.productservice.models.User;
import dev.hardeep.productservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequestDto requestDto) {
        return authService.register(requestDto);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequestDto requestDto) {
        return authService.login(requestDto);
    }

}
