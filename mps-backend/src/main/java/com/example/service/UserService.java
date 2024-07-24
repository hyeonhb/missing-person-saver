package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.Users;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDTO> getAllUsers();
    /**
     * 사용자 조회
     * @param userMap(telno, name) 사용자 전화번호, 이름
     * @return 기존에 존재하는 사용자가 있을 경우 해당 사용자, 없다면 새로 생성된 사용자
     */
    Users getUser(Map userMap);
}
