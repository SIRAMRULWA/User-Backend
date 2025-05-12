package com.sihlemrulwa.User_Backend.service;

// 4. Service Interface

import com.sihlemrulwa.User_Backend.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    User getUserByEmail(String email);
}
