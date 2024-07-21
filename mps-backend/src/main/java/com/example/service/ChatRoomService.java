package com.example.service;

import com.example.dto.ChatRoomDTO;
import com.example.dto.UserDTO;
import com.example.entity.ChatRoom;
import com.example.entity.Users;

import java.util.List;
import java.util.Map;

public interface ChatRoomService {
    List<UserDTO> getAllChatRoom();

    Long getChatRoom(ChatRoomDTO chatRoomDTO);
}
