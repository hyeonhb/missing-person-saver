package com.example.controller;

import com.example.entity.ChatRoom;
import com.example.entity.ChatRoomId;
import com.example.entity.PhotoReport;
import com.example.service.ChatRoomService;
import com.example.service.PhotoReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/photo-reports")
public class PhotoReportController {

    @Autowired
    private PhotoReportService photoReportService;

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping("/upload")
    public Map<String, String> uploadPhotoReport(@RequestBody MultipartFile file,
                                                 @RequestParam("roomId") UUID roomId) throws IOException {
        ChatRoom chatRoom = chatRoomService.getChatRoomEntity(roomId);
        return photoReportService.savePhotoReport(file, chatRoom);
    }
}