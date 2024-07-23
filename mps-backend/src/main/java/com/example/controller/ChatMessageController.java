package com.example.controller;

import com.example.dto.CompletionRequestDto;
import com.example.dto.RequestData;
import com.example.service.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat-message")
public class ChatMessageController {
    @Autowired
    private ChatGptService chatGptService;

    /**
     * [API] ChatGPT 모델 리스트를 조회합니다.
     */
    @GetMapping("/modelList")
    public ResponseEntity<List<Map<String, Object>>> selectModelList() {
        List<Map<String, Object>> result = chatGptService.modelList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * [API] ChatGPT 유효한 모델인지 조회합니다.
     *
     * @param modelName
     * @return
     */
    @GetMapping("/model")
    public ResponseEntity<Map<String, Object>> isValidModel(@RequestParam(name = "modelName") String modelName) {
        Map<String, Object> result = chatGptService.isValidModel(modelName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PostMapping("/generate")
//    public String generateText(@RequestBody RequestData requestData) {
//        return chatGptService.generateText(requestData.getQuestion(), requestData.getAdditionalInfo());
//    }

    @PostMapping("/prompt")
    public ResponseEntity<Map<String, Object>> selectPrompt(@RequestBody CompletionRequestDto completionRequestDto) {
        Map<String, Object> result = chatGptService.prompt(completionRequestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
