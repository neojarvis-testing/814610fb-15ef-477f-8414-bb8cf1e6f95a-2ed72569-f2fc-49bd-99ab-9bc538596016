package com.examly.springappuser.service;


import com.examly.springappuser.model.User;
import com.examly.springappuser.model.LoginDTO;

public interface UserService {
    User registerUser(User user);
    String loginUser(LoginDTO loginDTO);
}

