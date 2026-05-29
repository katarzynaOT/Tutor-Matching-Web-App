package org.example.tutormatch.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.tutormatch.dto.AuthLoginRequest;
import org.example.tutormatch.dto.AuthRegisterRequest;
import org.example.tutormatch.dto.AuthResponse;
import org.example.tutormatch.entity.*;
import org.example.tutormatch.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.tutormatch.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse register(AuthRegisterRequest dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());

        User saved = userRepository.save(user);

        if (dto.getRole() == Role.STUDENT) {
            Student student = new Student();
            student.setUser(saved);
            student.setUniversity("unknown");
            studentRepository.save(student);
        } else {
            Tutor tutor = new Tutor();
            tutor.setUser(saved);
            tutor.setBio("-");
            tutor.setSubject("-");
            tutorRepository.save(tutor);
        }

        String token = JwtUtil.generateToken(saved.getEmail());
        return new AuthResponse(
            saved.getId(),
            saved.getName(),
            saved.getEmail(),
            "REGISTER SUCCESS",
            token
        );
    }

    public AuthResponse login(AuthLoginRequest dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = JwtUtil.generateToken(user.getEmail());
        return new AuthResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            "LOGIN SUCCESS",
            token
        );
    }
}
