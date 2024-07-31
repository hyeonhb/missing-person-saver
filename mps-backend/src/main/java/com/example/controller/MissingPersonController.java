package com.example.controller;

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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

}
