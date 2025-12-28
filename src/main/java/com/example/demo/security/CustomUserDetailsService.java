// package com.example.demo.security;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.security.core.userdetails.*;
// import org.springframework.stereotype.Service;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     UserRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String email) {
//         User u = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new UsernameNotFoundException("not found"));

//         return org.springframework.security.core.userdetails.User
//                 .withUsername(u.getEmail())
//                 .password(u.getPassword())
//                 .authorities(u.getRoles().toArray(new String[0]))
//                 .build();
//     }
// }


package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // ❗ NOT final (tests inject via reflection)
    UserRepository userRepository;

    // ✅ REQUIRED by TestNG
    public CustomUserDetailsService() {
    }

    // ✅ Optional constructor (Spring compatible)
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User u = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(u.getEmail())
                .password(u.getPassword())
                .authorities(u.getRoles().toArray(new String[0]))
                .build();
    }
}
