package com.example.demo.service.impl;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        user.setActive(true);
        return userRepo.save(user);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }
}
