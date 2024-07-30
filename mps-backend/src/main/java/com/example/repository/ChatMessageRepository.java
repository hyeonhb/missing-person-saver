package com.example.repository;

import com.example.entity.ChatMessage;
import com.example.entity.ChatMessageId;
import com.example.entity.ChatRoom;
import com.example.entity.ChatRoomId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, ChatMessageId> {

    List<ChatMessage> findByChatRoom(ChatRoom chatRoom);
}
