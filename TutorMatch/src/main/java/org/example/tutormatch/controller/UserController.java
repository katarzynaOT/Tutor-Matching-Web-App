package org.example.tutormatch.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tutormatch.dto.UserCreateRequest;
import org.example.tutormatch.dto.UserResponse;
import org.example.tutormatch.entity.User;
import org.example.tutormatch.service.UserService;
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
    public List<UserResponse> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getOne(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
