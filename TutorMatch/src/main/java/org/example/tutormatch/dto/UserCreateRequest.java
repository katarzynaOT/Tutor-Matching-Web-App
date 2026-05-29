package org.example.tutormatch.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.example.tutormatch.entity.Role;

@Getter
@Setter
public class UserCreateRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Size(min = 6)
    private String password;

    @NotNull
    private Role role;
}