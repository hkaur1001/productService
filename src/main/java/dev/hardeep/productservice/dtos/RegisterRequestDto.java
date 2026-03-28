package dev.hardeep.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class RegisterRequestDto {

    String name;
    String email;
    String password;
}
