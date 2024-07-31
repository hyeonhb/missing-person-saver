package com.example.service;

import com.example.dto.PhotoReportDTO;
import com.example.entity.ChatRoom;
import com.example.entity.MissingPerson;
import com.example.entity.PhotoReport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PhotoReportService {
    Map<String, String> savePhotoReport(MultipartFile[] files, ChatRoom chatRoom) throws IOException;
    ResponseEntity<List<PhotoReportDTO>> getAllPhotoReportsByChatRoom(ChatRoom chatRoom);
    ResponseEntity<List<PhotoReportDTO>> getAllPhotoReportsByMissingPerson(Long mpId);

}
