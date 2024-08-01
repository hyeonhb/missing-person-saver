package com.example.controller;

import com.example.dto.PhotoReportDTO;
import com.example.entity.ChatRoom;
import com.example.entity.MissingPerson;
import com.example.service.ChatRoomService;
import com.example.service.MissingPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/missing-persons")
public class MissingPersonController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MissingPersonService missingPersonService;

    @GetMapping("/find-info")
    public ResponseEntity<Map<String, Object>> getMissingPersonList(@RequestParam UUID roomId) throws Exception {
        return missingPersonService.getMissingPersonInfo(setParamMap(roomId));
    }

    @GetMapping("/get-img")
    public ResponseEntity<byte[]> getMissingPersonImg(@RequestParam UUID roomId) throws Exception {
        return missingPersonService.getMissingPersonImg(setParamMap(roomId));
    }

    private Map<String, Object> setParamMap(@RequestParam UUID roomId) {
        ChatRoom chatRoom = chatRoomService.getChatRoomEntity(roomId);
        MissingPerson missingPerson = chatRoom.getMissingPerson();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("msspsnIdntfccd", missingPerson.getMpId());
        paramMap.put("nm", missingPerson.getName());
        return paramMap;
    }


    @GetMapping("/face-analyze")
    public String photoAnalyzeByMissingPerson(@RequestParam UUID roomId) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            String pythonCommand;
            if (os.contains("win")) {
                pythonCommand = "C:\\Path\\To\\Python\\python.exe"; // Windows에서의 절대 경로
            } else {
                pythonCommand = "/usr/bin/python"; // Unix 계열 OS에서의 절대 경로
            }

            List<String> command = new ArrayList<>();
            
            command.add(pythonCommand);
            command.add("/missing-person-saver/python-model/face_detection.py");
            command.add(roomId.toString());

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            reader.close();
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error running Python script";
        }
    }

}
