package org.example.tutormatch.service;

import jakarta.transaction.Transactional;
import org.example.tutormatch.dto.UserUpdateNameRequest;
import lombok.RequiredArgsConstructor;
import org.example.tutormatch.dto.UserCreateRequest;
import org.example.tutormatch.dto.UserResponse;
import org.example.tutormatch.entity.User;
import org.example.tutormatch.mapper.UserMapper;
import org.example.tutormatch.repository.UserRepository;
import org.springframework.stereotype.Service;
import jakarta.validation.constraints.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponse createUser(UserCreateRequest dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        User saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }


    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toResponse)
                .toList();
    }


    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toResponse(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    @Transactional
    public UserResponse updateUserName(String email, UserUpdateNameRequest dto) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(dto.getName());
        return UserMapper.toResponse(userRepository.save(user));
    }
}
