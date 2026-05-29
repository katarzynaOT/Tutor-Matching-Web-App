package org.example.tutormatch.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;

@Getter
@Setter
public class AuthLoginRequest {

    @Email
    private String email;

    private String password;
}
