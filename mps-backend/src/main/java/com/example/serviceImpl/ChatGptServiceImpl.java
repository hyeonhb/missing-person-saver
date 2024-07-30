package com.example.serviceImpl;

import com.example.config.OpenApiConfig;
import com.example.dto.CompletionRequestDto;
import com.example.service.ChatGptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    private static final String ENDPOINT = "https://api.openai.com/v1/completions";

    @Override
    public List<Map<String, Object>> modelList() {
        log.debug("[+] 모델 리스트를 조회합니다.");
        List<Map<String, Object>> resultList = null;

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = openApiConfig.httpHeaders();

        // [STEP2] 통신을 위한 RestTemplate을 구성합니다.
        ResponseEntity<String> response = restTemplate
                .exchange(openApiConfig.getModelUrl(), HttpMethod.GET, new HttpEntity<>(headers), String.class);
        try {
            // [STEP3] Jackson을 기반으로 응답값을 가져옵니다.
            ObjectMapper om = new ObjectMapper();
            Map<String, Object> data = om.readValue(response.getBody(), new TypeReference<>() {
            });

            // [STEP4] 응답 값을 결과값에 넣고 출력을 해봅니다.
            resultList.addAll((List<Map<String, Object>>) data.get("data"));
            for (Map<String, Object> object : resultList) {
                log.debug("ID: " + object.get("id"));
                log.debug("Object: " + object.get("object"));
                log.debug("Created: " + object.get("created"));
                log.debug("Owned By: " + object.get("owned_by"));
            }
        } catch (JsonMappingException e) {
            log.debug("JsonMappingException :: " + e.getMessage());
        } catch (JsonProcessingException e) {
            log.debug("JsonProcessingException :: " + e.getMessage());
        } catch (RuntimeException e) {
            log.debug("RuntimeException :: " + e.getMessage());
        }
        return resultList;
    }


    /**
     * 모델이 유효한지 확인하는 비즈니스 로직
     *
     * @param modelName {}
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> isValidModel(String modelName) {
        log.debug("[+] 모델이 유효한지 조회합니다. 모델 : " + modelName);
        Map<String, Object> result = new HashMap<>();

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = openApiConfig.httpHeaders();

        // [STEP2] 통신을 위한 RestTemplate을 구성합니다.
        ResponseEntity<String> response = restTemplate
                .exchange(openApiConfig.getModelListUrl() + "/" + modelName, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        try {
            // [STEP3] Jackson을 기반으로 응답값을 가져옵니다.
            ObjectMapper om = new ObjectMapper();
            result.putAll(om.readValue(response.getBody(), new TypeReference<>() {}));
        } catch (JsonProcessingException e) {
            log.debug("JsonMappingException :: " + e.getMessage());
        } catch (RuntimeException e) {
            log.debug("RuntimeException :: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> prompt(String msg) {
        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = openApiConfig.httpHeaders();

        String requestBody;
        ObjectMapper om = new ObjectMapper();

        // [STEP3] properties의 model을 가져와서 객체에 추가합니다.
        CompletionRequestDto requestDto = CompletionRequestDto.builder()
                .model("text-davinci-001")
                .prompt(msg)
                .temperature(0.8f)
                .build();

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
                        "https://api.openai.com/v1/completions",
                        HttpMethod.POST,
                        requestEntity,
                        String.class);

        Map<String, Object> resultMap = new HashMap<>();
        try {
            // [STEP6] String -> HashMap 역직렬화를 구성합니다.
            resultMap.putAll(om.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {}));
        } catch (JsonProcessingException e) {
            System.out.println("JsonMappingException :: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("RuntimeException :: " + e.getMessage());
        }
        return resultMap;
    }
}
