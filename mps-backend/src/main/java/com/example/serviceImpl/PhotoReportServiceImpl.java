package com.example.serviceImpl;

import com.example.dto.PhotoReportDTO;
import com.example.dto.UserDTO;
import com.example.entity.ChatRoom;
import com.example.entity.PhotoReport;
import com.example.entity.PhotoReportId;
import com.example.entity.Users;
import com.example.repository.PhotoReportRepository;
import com.example.service.PhotoReportService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PhotoReportServiceImpl implements PhotoReportService {
    // 파일 타입 제한
    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("image/jpeg", "image/png");

    @Autowired
    private PhotoReportRepository photoReportRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Map<String, String> savePhotoReport(MultipartFile[] files, ChatRoom chatRoom) throws IOException {
        Map<String, String> resultMap = new HashMap<>();

        try{
            for (MultipartFile file : files) {
                String fileType = file.getContentType();
                if (!ALLOWED_MIME_TYPES.contains(fileType)) {
                    resultMap.put("result", "N");
                    resultMap.put("msg", "지원되지 않는 파일 형식입니다. JPEG 또는 PNG 파일을 업로드해 주세요.");
                }
                String fileName = file.getOriginalFilename();
                Long fileSize = file.getSize();
                byte[] photoData = file.getBytes();
                PhotoReportId photoReportId = new PhotoReportId(UUID.randomUUID(), chatRoom.getChatRoomId());

                PhotoReport photoReport = new PhotoReport();
                photoReport.setId(photoReportId);
                photoReport.setChatRoom(chatRoom);
                photoReport.setPhoto(photoData);
                photoReport.setPhotoName(fileName);
                photoReport.setPhotoSize(fileSize);
                photoReport.setPhotoType(fileType);
                photoReport.setInsDt(LocalDateTime.now());

                entityManager.persist(photoReport);
            }
            resultMap.put("result", "Y");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultMap.put("result", "N");
            resultMap.put("msg", "파일 업로드에 실패하였습니다.");
        }

        return resultMap;
    }

    @Override
    public ResponseEntity<List<PhotoReportDTO>> getAllPhotoReportsByChatRoom(ChatRoom chatRoom) {
        List<PhotoReport> photoReports = photoReportRepository.findByChatRoom(chatRoom);
        if (photoReports.isEmpty()) { // 조회된 값이 없을 경우
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                photoReports.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PhotoReportDTO>> getAllPhotoReportsByMissingPerson(Long mpId) {
        List<PhotoReport> photoReports = photoReportRepository.findByIdChatRoomIdMpId(mpId);
        if (photoReports.isEmpty()) { // 조회된 값이 없을 경우
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(photoReports.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    private PhotoReportDTO convertEntityToDTO(PhotoReport photoReport) {
        PhotoReportDTO photoReportDTO = new PhotoReportDTO();
        return photoReportDTO.setDto(photoReport);
    }

}