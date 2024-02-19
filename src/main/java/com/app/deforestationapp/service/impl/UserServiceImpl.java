package com.app.deforestationapp.service.impl;

import com.app.deforestationapp.entity.User;
import com.app.deforestationapp.repository.UserRepository;
import com.app.deforestationapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) {
        // Check if a user with the given email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("Email already in use");
        }

        // If the email is not in use, proceed to save the new user
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found for this id :: " + id));
    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return "User not found";
        }

        if (!user.getPassword().equals(password)) {
            return "Invalid password";
        }

        if (Boolean.TRUE.equals(user.getIsLogged())) {
            return "User already logged in";
        } else {
            user.setIsLogged(true);
            userRepository.save(user);
            return "Login successful";
        }
    }

    @Override
    public String logout(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null || Boolean.FALSE.equals(user.getIsLogged())) {
            return "User not found or not logged in";
        } else {
            user.setIsLogged(false);
            userRepository.save(user);
            return "Logout successful";
        }
    }
}
