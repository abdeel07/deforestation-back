package com.app.deforestationapp.service;

import com.app.deforestationapp.entity.User;

public interface UserService {
    User add(User user);

    User get(Long id) throws Exception;

    String login(String email, String password);

    String logout(Long id);
}
