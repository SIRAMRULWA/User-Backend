package com.sihlemrulwa.User_Backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role; // Role of the user (e.g., ADMIN, USER, etc.)
    private LocalDateTime createdAt;

    // Default constructor
    public UserDto() {
    }

    // Optional: Constructor for quick assignment
    public UserDto(Long id, String firstName, String lastName, String email, String role, LocalDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }
}
