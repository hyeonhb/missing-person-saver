package com.example.serviceImpl;

import com.example.entity.ChatRoom;
import com.example.entity.PhotoReport;
import com.example.entity.PhotoReportId;
import com.example.repository.PhotoReportRepository;
import com.example.service.PhotoReportService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PhotoReportServiceImpl implements PhotoReportService {


    @Autowired
    private PhotoReportRepository photoReportRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Map<String, String> savePhotoReport(MultipartFile file, ChatRoom chatRoom) throws IOException {
        String fileName = file.getOriginalFilename();
        Long fileSize = file.getSize();
        String fileType = file.getContentType();
        byte[] photoData = file.getBytes();

        Map<String, String> resultMap = new HashMap<>();
        PhotoReportId photoReportId = new PhotoReportId(UUID.randomUUID(), chatRoom.getChatRoomId());

        try{
            PhotoReport photoReport = new PhotoReport();
            photoReport.setId(photoReportId);
            photoReport.setChatRoom(chatRoom);
            photoReport.setPhoto(photoData);
            photoReport.setPhotoName(fileName);
            photoReport.setPhotoSize(fileSize);
            photoReport.setPhotoType(fileType);
            photoReport.setInsDt(LocalDateTime.now());

            entityManager.persist(photoReport);
            resultMap.put("result", "Y");

        } catch (Exception e) {
            System.out.println(e);
            resultMap.put("result", "N");
        }

        return resultMap;
    }
}