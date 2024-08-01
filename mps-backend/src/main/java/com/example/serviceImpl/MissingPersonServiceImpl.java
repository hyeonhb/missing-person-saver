package com.example.serviceImpl;

import com.example.config.OpenApiConfig;
import com.example.entity.MissingPerson;
import com.example.repository.MissingPersonRepository;
import com.example.service.MissingPersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MissingPersonServiceImpl implements MissingPersonService {

    @Autowired
    private OpenApiConfig openApiConfig;

    @Autowired
    private MissingPersonRepository missingPersonRepository;

    /**
     * 실종자 id 로 정보 조회
     * @param id
     * @return MissingPerson
     */
    @Override
    public MissingPerson getMissingPerson(Long id) {
        Optional<MissingPerson> missingPersoneOptional = missingPersonRepository.findById(id);
        return missingPersoneOptional.get();
    }

    /**
     * 실종자 정보 조회
     *
     * @param queryParams(UUID roomId, String nm, String msspsnIdntfccd)
     * @return ResponseEntity<Map < String, Object>>
     */
    @Override
    public ResponseEntity getMissingPersonInfo(Map<String, Object> queryParams) throws Exception {
        queryParams.put("esntlId", openApiConfig.getApiId());
        queryParams.put("authKey", openApiConfig.getApiKey());
        queryParams.put("rowSize", "10");

        // 데이터를 가져옴 ([자료 출처: 경찰청])
        String urlString = "https://www.safe182.go.kr/api/lcm/amberList.do";
        try {
            String queryString = queryParams.entrySet().stream()
                    .map(entry -> {
                        try {
                            return entry.getKey() + "=" + entry.getValue();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.joining("&"));

            URL url = new URL(urlString + "?" + queryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // JSON 파싱
                Gson gson = new Gson();
                JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
                JsonArray list = jsonResponse.getAsJsonArray("list");

                JsonObject targetObject = null;
                for (int i = 0; i < list.size(); i++) {
                    JsonObject obj = list.get(i).getAsJsonObject();
                    if (obj.get("nm").getAsString().equals(queryParams.get("nm"))
                            && obj.get("msspsnIdntfccd").getAsString().equals(queryParams.get("msspsnIdntfccd").toString())) {
                        targetObject = obj;
                        break;
                    }
                }

                if (targetObject != null) {
                    // 결과를 Map에 추가
                    Map result = new HashMap<>();
                    result = new ObjectMapper().readValue(targetObject.toString(), Map.class);

                    // tknphotoFile 디코딩
                    String tknphotoFile = result.get("tknphotoFile").toString();
                    byte[] imageBytes = this.decodeImg(tknphotoFile);

                    result.put("tknphotoFile", imageBytes);

                    result.put("source", "[자료 출처: 경찰청]");

                    // 응답 헤더 설정
                    HttpHeaders headers = new HttpHeaders();
                    headers.set("Content-Type", "application/json");
                    return new ResponseEntity<>(result, headers, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 실종자 이미지 조회
     * @param queryParams (String nm, String msspsnIdntfccd)
     * @return ResponseEntity<byte[]>
     */
    @Override
    public ResponseEntity<byte[]> getMissingPersonImg(Map<String, Object> queryParams) throws Exception {
        ResponseEntity<Map<String, Object>> responseEntity = this.getMissingPersonInfo(queryParams);
        try {

            // Map에서 byte[] 값을 꺼내기
            Object value = responseEntity.getBody().get("tknphotoFile");

            if (value instanceof byte[]) {
                byte[] retrievedByteArray = (byte[]) value;
                // 이미지 데이터를 응답으로 반환
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "image/jpeg");
                headers.set("Content-Length", String.valueOf(retrievedByteArray.length));

                return new ResponseEntity<>(retrievedByteArray, headers, HttpStatus.OK);
            } else {
                System.out.println("The value is not a byte array.");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private byte[] decodeImg(String tknphotoFile) {
        tknphotoFile = tknphotoFile.replaceAll("\\s+", ""); // 모든 공백 문자 제거

        // Base64 디코딩
        return Base64.getDecoder().decode(tknphotoFile.getBytes());
    }
}