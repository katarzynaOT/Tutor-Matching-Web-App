package org.example.tutormatch.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    private Long id;
    private String name;
    private String email;
    private String message;
    private String token;

}
