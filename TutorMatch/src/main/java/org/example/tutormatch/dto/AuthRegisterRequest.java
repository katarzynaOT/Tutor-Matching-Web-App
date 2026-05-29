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
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotNull
    private Role role;
}