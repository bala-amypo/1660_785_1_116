// package com.example.demo.service;

// import com.example.demo.dto.UserDto;
// import com.example.demo.entity.User;

// import java.util.List;

// public interface UserService {

//     User createUser(User user);

//     User getUserById(Long id);

//     User getUserByEmail(String email);

//     List<User> getAllUsers();

//     UserDto convertToDto(User user);
// }


package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User createUser(User user);
    User findByEmail(String email);
}