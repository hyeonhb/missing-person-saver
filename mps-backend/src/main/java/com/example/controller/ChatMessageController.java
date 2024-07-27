package com.example.controller;

//import com.example.dto.CompletionRequestDto;
//import com.example.dto.RequestData;
//import com.example.service.ChatGptService;
import com.example.dto.ChatMessageDTO;
import com.example.entity.ChatRoom;
import com.example.service.ChatMessageService;
import com.example.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/chat-message")
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping("/test")
    public List<ChatMessageDTO> getMessageHistory(@RequestParam("roomId") UUID roomId) {
        ChatRoom chatRoom = chatRoomService.getChatRoomEntity(roomId);

        return chatMessageService.getMessageHistory(chatRoom);
    }

    @PostMapping("/save")
    public Map<String, String> saveMessage(@RequestParam("roomId") UUID roomId, @RequestBody Map<String, String> msgMap) {
        ChatRoom chatRoom = chatRoomService.getChatRoomEntity(roomId);

        return chatMessageService.saveMessage(chatRoom,msgMap);
    }
}
