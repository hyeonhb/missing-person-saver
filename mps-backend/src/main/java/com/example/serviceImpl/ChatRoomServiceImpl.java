package com.example.serviceImpl;

import com.example.dto.ChatRoomDTO;
import com.example.dto.UserDTO;
import com.example.entity.ChatRoom;
import com.example.entity.ChatRoomId;
import com.example.entity.MissingPerson;
import com.example.entity.Users;
import com.example.repository.ChatRoomRepository;
import com.example.service.ChatRoomService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserDTO> getAllChatRoom() {
        return null;
    }

    /**
     * 사용자, 실종자로 채팅방을 조회. 존재하지 않으면 주어진 값들로 새 채팅방을 생성
     * @param users
     * @param missingPerson
     * @return 기존에 존재하는 채팅방이 있을 경우 해당 채팅방, 없다면 새로 생성된 채팅방
     */
    @Override
    @Transactional
    public UUID getChatRoom(Users users, MissingPerson missingPerson) {
        // entity가 아닌 Long으로 조회하는 경우
//        Optional<ChatRoom> chatRoomOptional1 = chatRoomRepository.findByChatRoomIdUserIdAndChatRoomIdMpId(users.getUserId(), missingPerson.getMpId());
        // entity로 조회하는 경우
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findByUsersAndMissingPerson(users, missingPerson);

//        System.out.println(chatRoomOptional1.toString());
        System.out.println(chatRoomOptional.toString());
        if(chatRoomOptional.isEmpty()) {
            // UUID를 사용하여 roomId를 생성합니다.
            UUID roomId = UUID.randomUUID();
            ChatRoomId chatRoomId = new ChatRoomId(roomId, users.getUserId(), missingPerson.getMpId());

            ChatRoom newChatRoom = new ChatRoom();
            newChatRoom.setChatRoomId(chatRoomId);
            newChatRoom.setUsers(users);
            newChatRoom.setMissingPerson(missingPerson);
            newChatRoom.setInsDt(LocalDateTime.now());

            entityManager.persist(newChatRoom);

            return roomId;
        } else {
            return chatRoomOptional.get().getChatRoomId().getRoomId();
        }
    }

    @Override
    public ChatRoom getChatRoomEntity(UUID roomId) {
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findByChatRoomIdRoomId(roomId);

        return chatRoomOptional.get();
    }

}
