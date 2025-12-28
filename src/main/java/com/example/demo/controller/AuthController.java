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
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService,
                          JwtTokenProvider jwtTokenProvider,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());

        User savedUser = userService.save(user);

        String token = jwtTokenProvider.generateToken(savedUser);
        return ResponseEntity.ok(token);
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {

        User user = userService.getUserByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(user);
        return ResponseEntity.ok(token);
    }
}
