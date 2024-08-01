package com.example.serviceImpl;

import com.example.dto.ChatMessageDTO;
import com.example.entity.ChatMessage;
import com.example.entity.ChatMessageId;
import com.example.entity.ChatRoom;
import com.example.entity.ChatRoomId;
import com.example.repository.ChatMessageRepository;
import com.example.service.ChatMessageService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<ChatMessageDTO> getMessageHistory(ChatRoom chatRoom) {

        List<ChatMessage> chatMessageOptional = chatMessageRepository.findByChatRoomOrderByInsDt(chatRoom);

        System.out.println(chatMessageOptional.stream().toList());

        return chatMessageOptional.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Map<String, String> saveMessage(ChatRoom chatRoom, Map<String, String> msgMap) {
        UUID msgId = UUID.randomUUID();

        Map<String, String> resultMap = new HashMap<>();
        ChatMessageId chatMessageId = new ChatMessageId(msgId, chatRoom.getChatRoomId());

        try {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setId(chatMessageId);
            chatMessage.setChatRoom(chatRoom);
            chatMessage.setContent(msgMap.get("content"));
            chatMessage.setType(Long.parseLong(msgMap.get("type")));
            chatMessage.setInsDt(LocalDateTime.now());

            entityManager.persist(chatMessage);

            resultMap.put("result", "Y");
        } catch (Exception e) {
            System.out.println(e);

            resultMap.put("result", "N");
        }

        return resultMap;
    }

    private ChatMessageDTO convertEntityToDTO(ChatMessage chatMessage) {
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        chatMessageDTO.setMsgId(chatMessage.getId());
        chatMessageDTO.setContent(chatMessage.getContent());
        chatMessageDTO.setType(chatMessage.getType());
        chatMessageDTO.setInsDt(chatMessage.getInsDt());

        return chatMessageDTO;
    }

}
