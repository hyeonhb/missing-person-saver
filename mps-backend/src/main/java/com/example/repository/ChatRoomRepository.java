package com.example.repository;

import com.example.entity.ChatRoom;
import com.example.entity.ChatRoomId;
import com.example.entity.MissingPerson;
import com.example.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, ChatRoomId> {
//    @Query("SELECT e FROM ChatRoom e WHERE e.userId = :userId AND e.mpId = :mpId")
//    Optional<ChatRoom> findByUserIdAndMpId(@Param("userId") Long userId, @Param("mpId") Long mpId);

    Optional<ChatRoom> findByUsersAndMissingPerson(Users users, MissingPerson missingPerson);
}
