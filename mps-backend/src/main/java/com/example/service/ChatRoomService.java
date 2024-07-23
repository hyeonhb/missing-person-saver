package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.MissingPerson;
import com.example.entity.Users;

import java.util.List;
import java.util.UUID;

public interface ChatRoomService {
    List<UserDTO> getAllChatRoom();

    UUID getChatRoom(Users users, MissingPerson missingPerson);
}
