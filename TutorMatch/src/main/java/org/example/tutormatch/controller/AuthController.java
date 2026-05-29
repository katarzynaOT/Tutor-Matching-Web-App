package org.example.tutormatch.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tutormatch.dto.AuthLoginRequest;
import org.example.tutormatch.dto.AuthRegisterRequest;
import org.example.tutormatch.dto.AuthResponse;
import org.example.tutormatch.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid AuthRegisterRequest dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthLoginRequest dto) {
        return authService.login(dto);
    }
}