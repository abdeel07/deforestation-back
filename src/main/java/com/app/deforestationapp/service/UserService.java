package com.app.deforestationapp.service;

import com.app.deforestationapp.entity.User;

import java.util.Map;

public interface UserService {
    User add(User user) throws Exception;

    User get(Long id) throws Exception;

    Map<String, Object> login(String email, String password);

    String logout(Long id);
}
