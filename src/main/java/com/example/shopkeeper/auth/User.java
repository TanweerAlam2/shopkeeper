package com.example.shopkeeper.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 80)
    private String fullName;

    @Column(nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(nullable = false)
    private String password;

    public User() {}
    public User(String fullName, String email, String password) {
        this.fullName = fullName; this.email = email; this.password = password;
    }
    public Long getId(){ return id; }
    public String getFullName(){ return fullName; }
    public void setFullName(String fullName){ this.fullName=fullName; }
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email=email; }
    public String getPassword(){ return password; }
    public void setPassword(String password){ this.password=password; }
}
