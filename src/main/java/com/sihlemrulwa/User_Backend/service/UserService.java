package com.sihlemrulwa.User_Backend.service;

import com.sihlemrulwa.User_Backend.dto.UserDto;
import com.sihlemrulwa.User_Backend.dto.UserCreationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDto createUser(UserCreationDto userCreationDto);
    Page<UserDto> getAllUsers(Pageable pageable);
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserCreationDto userDetails);
    void deleteUser(Long id);
    UserDto getUserByEmail(String email);

    /**
     * Search for users based on multiple criteria
     *
     * @param name Optional name filter
     * @param email Optional email filter
     * @param role Optional role filter
     * @param status Optional status filter
     * @param minAge Optional minimum age filter
     * @param maxAge Optional maximum age filter
     * @return List of matching users
     */
    List<UserDto> findUsers(String name, String email, String role,
                            String status, Integer minAge, Integer maxAge);
}