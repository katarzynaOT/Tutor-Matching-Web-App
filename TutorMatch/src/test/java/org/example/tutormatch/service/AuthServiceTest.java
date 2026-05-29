package org.example.tutormatch.service;

import org.example.tutormatch.dto.*;
import org.example.tutormatch.entity.*;
import org.example.tutormatch.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    // REGISTER FLOW
    @Test
    void shouldRegisterUserSuccessfully() {
        // given
        AuthRegisterRequest dto = new AuthRegisterRequest();
        dto.setName("Anna");
        dto.setEmail("anna@test.com");
        dto.setPassword("123456");
        dto.setRole(Role.TUTOR);

        when(userRepository.findByEmail("anna@test.com"))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode("123456"))
                .thenReturn("hashedPassword");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Anna");
        savedUser.setEmail("anna@test.com");
        savedUser.setPassword("hashedPassword");
        savedUser.setRole(Role.TUTOR);

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        // when
        AuthResponse response = authService.register(dto);

        // then
        assertEquals("Anna", response.getName());
        assertEquals("anna@test.com", response.getEmail());
        assertEquals("REGISTER SUCCESS", response.getMessage());

        // verify
        verify(userRepository).save(any(User.class));
    }

    // LOGN FLOW
    @Test
    void shouldLoginSuccessfully() {
        // given
        AuthLoginRequest dto = new AuthLoginRequest();
        dto.setEmail("anna@test.com");
        dto.setPassword("123456");

        User user = new User();
        user.setId(1L);
        user.setName("Anna");
        user.setEmail("anna@test.com");
        user.setPassword("hashedPassword");
        user.setRole(Role.TUTOR);

        when(userRepository.findByEmail("anna@test.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("123456", "hashedPassword"))
                .thenReturn(true);

        // when
        AuthResponse response = authService.login(dto);

        // then
        assertEquals("Anna", response.getName());
        assertEquals("LOGIN SUCCESS", response.getMessage());
    }
}
