package com.example.controller;

import com.example.dto.PhotoReportDTO;
import com.example.entity.ChatRoom;
import com.example.entity.MissingPerson;
import com.example.entity.PhotoReport;
import com.example.service.ChatRoomService;
import com.example.service.MissingPersonService;
import com.example.service.PhotoReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/photo-reports")
public class PhotoReportController {

    @Autowired
    private PhotoReportService photoReportService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MissingPersonService missingPersonService;

    @GetMapping
    public ResponseEntity<List<PhotoReportDTO>> getAllPhotoReportsByChatRoom(@RequestParam UUID roomId) {
        ChatRoom chatRoom = chatRoomService.getChatRoomEntity(roomId);
        return photoReportService.getAllPhotoReportsByChatRoom(chatRoom);
    }

    @GetMapping("/by-mp")
    public ResponseEntity<List<PhotoReportDTO>> getAllPhotoReportsByMissingPerson(@RequestParam Long mpId) {
        return photoReportService.getAllPhotoReportsByMissingPerson(mpId);
    }

    @PostMapping("/upload")
    public Map<String, String> uploadPhotoReport(
            @RequestParam("roomId") UUID roomId, @RequestBody MultipartFile[] files) throws IOException {
        ChatRoom chatRoom = chatRoomService.getChatRoomEntity(roomId);
        return photoReportService.savePhotoReport(files, chatRoom);
    }
}