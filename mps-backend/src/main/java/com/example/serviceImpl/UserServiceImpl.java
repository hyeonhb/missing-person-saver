package com.example.serviceImpl;

import com.example.dto.UserDTO;
import com.example.entity.Users;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Users getUser(Map userMap) {
        String name = userMap.get("name").toString();
        String telno = userMap.get("telno").toString();
        Optional<Users> userOptional = userRepository.findByNameAndTelno(name, telno);
        if(userOptional.isEmpty()) {
            Users userEntity = new Users();
            userEntity.setName(name);
            userEntity.setTelno(telno);
            return userRepository.save(userEntity);
        }
        return userOptional.get();
    }

    private UserDTO convertEntityToDTO(Users users) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(users.getUserId());
        userDTO.setTelno(users.getTelno());
        userDTO.setName(users.getName());
        return userDTO;
    }
}