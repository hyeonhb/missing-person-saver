package com.example.service;

import com.example.entity.ChatRoom;
import com.example.entity.PhotoReport;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface PhotoReportService {
    Map<String, String> savePhotoReport(MultipartFile file, ChatRoom chatRoom) throws IOException;

}
