package com.app.deforestationapp.service.impl;

import com.app.deforestationapp.entity.User;
import com.app.deforestationapp.repository.UserRepository;
import com.app.deforestationapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) throws Exception {
        // Check if a user with the given email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already in use");
        }

        // If the email is not in use, proceed to save the new user
        user.setIsLogged(false);
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found for this id :: " + id));
    }

    @Override
    public Map<String, Object> login(String email, String password) {
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findByEmail(email).orElse(null);

        // Check if the user exists
        if (user == null) {
            response.put("success", false);
            response.put("message", "User not found");
            return response; // Early return to avoid further checks
        }

        // Check if the password is correct
        if (!user.getPassword().equals(password)) { // Consider using password encoding and matching
            response.put("success", false);
            response.put("message", "Invalid password");
            return response; // Early return to avoid further checks
        }

        // Check if the user is already logged in
        if (Boolean.TRUE.equals(user.getIsLogged())) {
            response.put("success", false);
            response.put("message", "User already logged in");
        } else {
            // Log the user in
            user.setIsLogged(true);
            userRepository.save(user);
            response.put("success", true);
            response.put("userId", user.getId());
            response.put("message", "Login successful");
        }

        return response;
    }


    @Override
    public String logout(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null || Boolean.FALSE.equals(user.getIsLogged())) {
            return "User not found or not logged in";
        } else {
            user.setIsLogged(false);
            userRepository.save(user);
            return "False";
        }
    }
}
