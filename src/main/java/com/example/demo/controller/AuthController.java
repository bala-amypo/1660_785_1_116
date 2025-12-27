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


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          JwtTokenProvider jwtTokenProvider,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public User register(@RequestBody AuthRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // ✅ ONLY VALID roles assignment
        user.setRoles(Set.of("ROLE_USER"));

        return userRepository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String rolesCsv = String.join(",", user.getRoles());

        return jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                rolesCsv
        );
    }
}
