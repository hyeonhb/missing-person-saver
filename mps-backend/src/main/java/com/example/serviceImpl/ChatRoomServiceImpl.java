package com.example.serviceImpl;

import com.example.dto.ChatRoomDTO;
import com.example.dto.UserDTO;
import com.example.entity.ChatRoom;
import com.example.entity.ChatRoomId;
import com.example.entity.Users;
import com.example.repository.ChatRoomRepository;
import com.example.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;


    @Override
    public List<UserDTO> getAllChatRoom() {
        return null;
    }

    @Override
    public Long getChatRoom(ChatRoomDTO chatRoomDTO) {
        ChatRoom chatRoom = new ChatRoom();
        Long roomId = null;
//        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findByUserIdAndMpId(chatRoomDTO.getUserId(), chatRoomDTO.getMpId());
        ChatRoomId chatRoomId = new ChatRoomId(chatRoomDTO.getRoomId(), chatRoomDTO.getUserId(), chatRoomDTO.getMpId());
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findById(chatRoomId);
        if(chatRoomOptional.isEmpty()) {
            ChatRoom chatRoomEntity = new ChatRoom();
//            ChatRoomId chatRoomId = new ChatRoomId();
            chatRoomEntity.setUserId(chatRoomDTO.getUserId());
            chatRoomEntity.setMpId(chatRoomDTO.getMpId());
//            chatRoomEntity.setChatRoomId(chatRoomId);
            ChatRoom newChatRoom = chatRoomRepository.save(chatRoomEntity);
            roomId = newChatRoom.getRoomId();
        }
        roomId = chatRoomOptional.get().getRoomId();
        return roomId;
    }

}
