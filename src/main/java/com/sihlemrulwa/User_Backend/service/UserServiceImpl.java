package com.sihlemrulwa.User_Backend.service;

import com.sihlemrulwa.User_Backend.dto.UserDto;
import com.sihlemrulwa.User_Backend.dto.UserCreationDto;
import com.sihlemrulwa.User_Backend.exception.ResourceNotFoundException;
import com.sihlemrulwa.User_Backend.mapper.UserMapper;
import com.sihlemrulwa.User_Backend.model.User;
import com.sihlemrulwa.User_Backend.repository.UserRepository;
import com.sihlemrulwa.User_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection (preferred over @Autowired)
    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserCreationDto userCreationDto) {
        // Check if email already exists
        if (userRepository.existsByEmail(userCreationDto.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        // Convert DTO to User entity
        User user = new User();
        user.setFirstName(userCreationDto.getFirstName());
        user.setLastName(userCreationDto.getLastName());
        user.setEmail(userCreationDto.getEmail());

        // Hash the password before storing
        user.setPassword(passwordEncoder.encode(userCreationDto.getPassword()));

        // Save and return DTO
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toUserDto);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserCreationDto userDetails) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Update fields
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());

        // Check if email is being changed and is unique
        if (!existingUser.getEmail().equals(userDetails.getEmail())) {
            if (userRepository.existsByEmail(userDetails.getEmail())) {
                throw new IllegalArgumentException("Email is already in use");
            }
            existingUser.setEmail(userDetails.getEmail());
        }

        // Update password if provided
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return userMapper.toUserDto(user);
    }
}
