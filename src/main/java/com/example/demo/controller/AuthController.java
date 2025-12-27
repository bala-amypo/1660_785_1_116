// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.security.JwtTokenProvider;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import java.util.HashSet;
// import java.util.Set;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final UserRepository userRepository;
//     private final JwtTokenProvider jwtTokenProvider;
//     private final PasswordEncoder passwordEncoder;   

//     public AuthController(UserRepository userRepository,
//                           JwtTokenProvider jwtTokenProvider,
//                           PasswordEncoder passwordEncoder) { 
//         this.userRepository = userRepository;
//         this.jwtTokenProvider = jwtTokenProvider;
//         this.passwordEncoder = passwordEncoder;
//     }

//     @PostMapping("/register")
//     public User register(@RequestBody AuthRequest request) {

//         if (userRepository.existsByEmail(request.getEmail())) {
//             throw new RuntimeException("email exists");
//         }

//         Set<String> roles = new HashSet<>();
//         roles.add("ROLE_USER");

//         User user = User.builder()
//                 .email(request.getEmail())
//                 .password(passwordEncoder.encode(request.getPassword()))
//                 .roles(roles)
//                 .build();

//         return userRepository.save(user);
//     }

//     @PostMapping("/login")
//     public String login(@RequestBody AuthRequest request) {

//         User user = userRepository.findByEmail(request.getEmail())
//                 .orElseThrow(() -> new RuntimeException("Invalid credentials"));

//         if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//             throw new RuntimeException("Invalid credentials");
//         }

//         return jwtTokenProvider.generateToken(
//                 user.getId(),
//                 user.getEmail(),
//                 user.getRoles()
//         );
//     }
// }



package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of("ROLE_USER"))
                .build();
        
        User savedUser = userRepository.save(user);
        
        String token = jwtTokenProvider.generateToken(
                savedUser.getId(), 
                savedUser.getEmail(), 
                savedUser.getRoles()
        );
        
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setEmail(savedUser.getEmail());
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        String token = jwtTokenProvider.generateToken(
                user.getId(), 
                user.getEmail(), 
                user.getRoles()
        );
        
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setEmail(user.getEmail());
        
        return ResponseEntity.ok(response);
    }
}