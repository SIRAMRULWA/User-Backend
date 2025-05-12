package com.sihlemrulwa.User_Backend.service;

import com.sihlemrulwa.User_Backend.dto.UserDto;
import com.sihlemrulwa.User_Backend.dto.UserCreationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto createUser(UserCreationDto userCreationDto);
    Page<UserDto> getAllUsers(Pageable pageable);
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserCreationDto userDetails);
    void deleteUser(Long id);
    UserDto getUserByEmail(String email);
}
