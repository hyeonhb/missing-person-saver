package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.MissingPerson;
import com.example.entity.Users;

import java.util.List;
import java.util.UUID;

public interface ChatRoomService {
    List<UserDTO> getAllChatRoom();

    /**
     * 채팅방 조회
     * @param users
     * @param missingPerson
     * @return 채팅방 UUID
     */
    UUID getChatRoom(Users users, MissingPerson missingPerson);
}
