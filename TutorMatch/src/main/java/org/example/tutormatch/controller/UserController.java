package org.example.tutormatch.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tutormatch.dto.UserCreateRequest;
import org.example.tutormatch.dto.UserResponse;
import org.example.tutormatch.dto.UserUpdateNameRequest;
import org.example.tutormatch.entity.User;
import org.example.tutormatch.mapper.UserMapper;
import org.example.tutormatch.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserCreateRequest dto) {
        return userService.createUser(dto);
    }

    @GetMapping
    public List<UserResponse> getAll(@RequestParam(value = "email", required = false) String email) {
        if (email != null) {
            User user = userService.getUserEntityByEmail(email);
            return List.of(UserMapper.toResponse(user));
        }
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getOne(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/me")
    public void delete(Authentication auth) {
        String email = auth.getName();
        userService.deleteByEmail(email);
    }

    @PutMapping("/me")
    public UserResponse updateName(
            @Valid @RequestBody UserUpdateNameRequest dto,
            Authentication authentication
    ) {
        String email = authentication.getName();
        return userService.updateUserName(email, dto);
    }
}
