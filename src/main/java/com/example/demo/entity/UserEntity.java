package com.example.demo.entity;

public class UserEntity{
    private Long id;
    private String email;
    private String password;
    private String roles;
    private LocalDateTime createdAt;

     public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public UserEntity(Long id, String email, String password, String roles, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.createdAt = createdAt;
    }
    public UserEntity() {
    }
    
}