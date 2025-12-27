// package com.example.demo.dto;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class UserDto {

//     private Long id;
//     private String email;
//     private String role;
//     private Boolean active;
// }


package com.example.demo.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String email;
    private Set<String> roles;
}