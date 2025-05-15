package com.sihlemrulwa.User_Backend.mapper;

import com.sihlemrulwa.User_Backend.dto.UserDto;
import com.sihlemrulwa.User_Backend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole()); // ✅ Map role
        userDto.setCreatedAt(user.getCreatedAt());
        return userDto;
    }
}
