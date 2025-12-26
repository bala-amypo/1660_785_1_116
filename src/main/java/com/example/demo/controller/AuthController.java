package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody AuthRequest request) {

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(Set.of("ROLE_USER"))
                .build();

        User saved = userService.register(user);

        UserDto dto = new UserDto();
        dto.setId(saved.getId());
        dto.setEmail(saved.getEmail());
        dto.setRoles(saved.getRoles());
        return dto;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        User user = userService.findByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String rolesCsv = user.getRoles().stream()
                .collect(Collectors.joining(","));

        String token = jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                rolesCsv
        );

        return new AuthResponse(token);
    }
}
