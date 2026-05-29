package org.example.tutormatch.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.*;
import org.example.tutormatch.entity.Role;

@Getter
@Setter
public class AuthRegisterRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Size(min = 6)
    private String password;

    @NotNull
    private Role role;
}