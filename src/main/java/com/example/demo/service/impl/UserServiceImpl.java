// package com.example.demo.service.impl;

// import com.example.demo.dto.UserDto;
// import com.example.demo.entity.User;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class UserServiceImpl implements UserService {

//     UserRepository userRepository;

//     @Override
//     public User createUser(User user) {
//         return userRepository.save(user);
//     }

//     @Override
//     public User getUserById(Long id) {
//         return userRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//     }

//     @Override
//     public User getUserByEmail(String email) {
//         return userRepository.findByEmail(email)
//                 .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//     }

//     @Override
//     public List<User> getAllUsers() {
//         return userRepository.findAll();
//     }

//     @Override
//     public UserDto convertToDto(User user) {
//         return new UserDto(
//                 user.getId(),
//                 user.getEmail(),
//                 user.getRoles().iterator().next(),
//                 true
//         );
//     }
// }


package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
    }
}