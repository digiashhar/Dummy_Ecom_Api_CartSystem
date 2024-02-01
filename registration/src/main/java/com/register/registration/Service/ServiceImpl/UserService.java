// UserService.java
package com.register.registration.Service.ServiceImpl;

import com.register.registration.dto.UserDTO;
import com.register.registration.entity.User;
import com.register.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        newUser.setMobile(userDTO.getMobile());
        return userRepository.save(newUser);
    }

    public User updateUser(Long id, UserDTO updatedUserDTO) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(updatedUserDTO.getUsername());
            existingUser.setPassword(updatedUserDTO.getPassword());
            existingUser.setMobile(updatedUserDTO.getMobile());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public ResponseEntity<String> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body("Deleted Successfully");
    }

    public User getUserByName(String name) {
        return userRepository.findUserByusername(name);
    }

    // SIGN-IN:
    public boolean signIn(long mobile, String password) {
        // Implement your logic to check if the mobile and password match
        // For simplicity, let's assume you have a method in the repository to check this
        User user = userRepository.findByMobileAndPassword(mobile, password);
        return user != null;
    }
}
