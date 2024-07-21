package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.Users;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDTO> getAllUsers();
    Users getUser(Map userMap);
}
