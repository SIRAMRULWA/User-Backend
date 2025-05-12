package com.sihlemrulwa.User_Backend.controller;

import com.sihlemrulwa.User_Backend.dto.UserDto;
import com.sihlemrulwa.User_Backend.dto.UserCreationDto;
import com.sihlemrulwa.User_Backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create a new user
     *
     * @param userCreationDto User details for registration
     * @return Created user details
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(
            @Valid @RequestBody UserCreationDto userCreationDto
    ) {
        UserDto createdUser = userService.createUser(userCreationDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Retrieve all users with pagination and sorting
     *
     * @param pageable Pagination and sorting parameters
     * @return Page of users
     */
    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(
            @PageableDefault(
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        Page<UserDto> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    /**
     * Get a user by their ID
     *
     * @param id User's unique identifier
     * @return User details
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable Long id
    ) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Get a user by their email address
     *
     * @param email User's email address
     * @return User details
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(
            @PathVariable String email
    ) {
        UserDto user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    /**
     * Update an existing user
     *
     * @param id User's unique identifier
     * @param userDetails Updated user information
     * @return Updated user details
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserCreationDto userDetails
    ) {
        UserDto updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete a user by their ID
     *
     * @param id User's unique identifier
     * @return Response indicating successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Partial update for a user (PATCH method)
     *
     * @param id User's unique identifier
     * @param userDetails Partial user update information
     * @return Updated user details
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> partialUpdateUser(
            @PathVariable Long id,
            @RequestBody UserCreationDto userDetails
    ) {
        // Reuse update method, but could implement more granular logic if needed
        UserDto updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Check if an email is already in use
     *
     * @param email Email to check
     * @return Boolean indicating email availability
     */
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailAvailability(
            @RequestParam String email
    ) {
        try {
            userService.getUserByEmail(email);
            return ResponseEntity.ok(false); // Email exists
        } catch (Exception e) {
            return ResponseEntity.ok(true); // Email is available
        }
    }
}