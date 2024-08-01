package com.example.repository;

import com.example.entity.ChatRoom;
import com.example.entity.PhotoReport;
import com.example.entity.PhotoReportId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoReportRepository extends JpaRepository<PhotoReport, PhotoReportId> {
    // chatRoom entity로 사진 조회
    List<PhotoReport> findByChatRoom(ChatRoom chatRoom);

    // missingPerson으로 사진 조회
    List<PhotoReport> findByIdChatRoomIdMpId(Long mpId);
}
