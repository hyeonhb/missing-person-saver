package com.example.service;

import com.example.dto.CompletionRequestDto;
import com.example.dto.RequestData;

import java.util.List;
import java.util.Map;

public interface ChatGptService {
    Map<String, Object> isValidModel(String modelName);
    List<Map<String, Object>> modelList();
//    String generateText(String prompt, String additionalInfo);

    Map<String, Object> prompt(String msg);
}
