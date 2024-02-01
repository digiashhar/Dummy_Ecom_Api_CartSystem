package com.register.registration.Service;

import com.register.registration.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterface {

    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User updatedUser);

    ResponseEntity<String> deleteUser(Long id);

    User getUserByName(String name);

}
