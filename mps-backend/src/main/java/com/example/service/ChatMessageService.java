package com.example.service;

import com.example.dto.ChatMessageDTO;
import com.example.entity.ChatRoom;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface ChatMessageService {
    List<ChatMessageDTO> getMessageHistory(ChatRoom chatRoom);

    Map<String,String> saveMessage(ChatRoom chatRoom, Map<String, String> msgMap);
}
