package com.example.serviceImpl;

import com.example.config.OpenApiConfig;
import com.example.service.ChatGptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGptServiceImpl implements ChatGptService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OpenApiConfig openApiConfig;

    private static final Logger log = LoggerFactory.getLogger(ChatGptServiceImpl.class);

    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions";

    @Override
    public Map<String, Object> prompt(String msg) {
        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = openApiConfig.httpHeaders();

        String requestBody;
        ObjectMapper om = new ObjectMapper();

        // [STEP3] properties의 model을 가져와서 객체에 추가합니다.
        Map<String, Object> requestDto = new HashMap<>();
        requestDto.put("model", openApiConfig.getGptModel());
        requestDto.put("messages", createMessages(msg));
        requestDto.put("temperature", 0.8);

        try {
            // [STEP4] Object -> String 직렬화를 구성합니다.
            requestBody = om.writeValueAsString(requestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate
                .exchange(
                        ENDPOINT,
                        HttpMethod.POST,
                        requestEntity,
                        String.class);

        Map<String, Object> resultMap = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            resultMap.putAll(objectMapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {}));
            List<Map<String, Object>> choices = (List<Map<String, Object>>) resultMap.get("choices");
            if (choices != null && !choices.isEmpty()) {
                Map<String, Object> choice = choices.get(0);
                Map<String, Object> message = (Map<String, Object>) choice.get("message");
                resultMap.put("result", "Y");
                resultMap.put("msg", message.get("content"));
            }
        } catch (JsonProcessingException e) {
            System.out.println("JsonMappingException :: " + e.getMessage());
            resultMap.put("result", "N");
            resultMap.put("msg", "답변을 생성하는 데 문제가 발생했습니다.");
        } catch (RuntimeException e) {
            System.out.println("RuntimeException :: " + e.getMessage());
            resultMap.put("result", "N");
            resultMap.put("msg", "답변을 생성하는 데 문제가 발생했습니다.");
        }
        return resultMap;
    }

    private List<Map<String, String>> createMessages(String question) {
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(createMessage("user", question));
        return messages;
    }

    private Map<String, String> createMessage(String role, String content) {
        Map<String, String> message = new HashMap<>();
        message.put("role", role);
        message.put("content", content);
        return message;
    }
}
