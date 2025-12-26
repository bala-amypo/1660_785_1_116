package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository,
                          JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("email exists");
        }

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );
    }
}
