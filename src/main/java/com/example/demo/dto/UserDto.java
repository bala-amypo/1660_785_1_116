package com.example.demo.dto;

import lombok.Data;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String email;
    private Set<String> roles;
}