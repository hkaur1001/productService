package dev.hardeep.productservice.services;

import dev.hardeep.productservice.dtos.LoginRequestDto;
import dev.hardeep.productservice.dtos.RegisterRequestDto;
import dev.hardeep.productservice.models.User;
import dev.hardeep.productservice.repositories.UserRepository;
import dev.hardeep.productservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public User register(RegisterRequestDto requestDto) {

        Optional<User> existing = userRepository.findByEmail(requestDto.getEmail());
        if(existing.isPresent())
            throw new RuntimeException("User already exists");

        User user = new User();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequestDto requestDto) {
        Optional<User> optionalUser = userRepository.findByEmail(requestDto.getEmail());
        if(!optionalUser.isPresent())
            throw new RuntimeException("User not found");

        User user = optionalUser.get();
        if(!user.getPassword().equals(requestDto.getPassword()))
            throw new RuntimeException("Invalid Password");

        return jwtUtil.generateToken(user.getEmail());
    }
}
