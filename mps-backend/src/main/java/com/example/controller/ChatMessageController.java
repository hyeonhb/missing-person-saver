package com.example.controller;

import com.example.dto.ChatMessageDTO;
import com.example.entity.ChatRoom;
import com.example.service.ChatGptService;
import com.example.service.ChatMessageService;
import com.example.service.ChatRoomService;
import com.example.service.MissingPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/chat-message")
public class ChatMessageController {

    @Autowired
    private MissingPersonService missingPersonService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatGptService chatGptService;

    @GetMapping("/get-messages")
    public List<ChatMessageDTO> getMessageHistory(@RequestParam("roomId") UUID roomId) {
        ChatRoom chatRoom = chatRoomService.getChatRoomEntity(roomId);

        return chatMessageService.getMessageHistory(chatRoom);
    }

    @PostMapping("/save")
    public Map<String, String> saveMessage(@RequestParam("roomId") UUID roomId, @RequestBody Map<String, String> msgMap) {
        ChatRoom chatRoom = chatRoomService.getChatRoomEntity(roomId);

        return chatMessageService.saveMessage(chatRoom,msgMap);
    }

    @PostMapping("/ask-basic-info")
    public Map<String, Object> askBasicInfo(@RequestParam("roomId") UUID roomId, @RequestBody Map<String, String> request) {
        String userQuestion = request.get("message");
        String base = "";
        if (request.get("type").equals("1")) {
            String faceYn = missingPersonService.photoAnalyzeByMissingPerson(roomId);
            if (request.get("detailType").equals("1") && faceYn.equals("Y")) {
                // 사진 특징점 분석 메서드 호출 하는 곳(String으로 온다고 가정)
//                base =  사진 특징점 분석 메서드 호출 하는 곳(String으로 온다고 가정)
            }
        }

        Map<String, Object> answer = chatGptService.prompt(userQuestion, base);
        if (!answer.isEmpty()) {
            if (answer.get("result").equals("Y")) {
                ChatRoom chatRoom = chatRoomService.getChatRoomEntity(roomId);
                Map<String, String> msgMap = new HashMap<String, String>();
                msgMap.put("content", answer.get("msg").toString());
                msgMap.put("type", "2");

                chatMessageService.saveMessage(chatRoom,msgMap);
            }
        }

        return answer;
    }
}
