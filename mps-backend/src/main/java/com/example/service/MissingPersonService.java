package com.example.service;

import com.example.entity.MissingPerson;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface MissingPersonService {
    /**
     * 실종자 조회
     * @param id
     * @return MissingPerson
     */
    MissingPerson getMissingPerson(Long id);

    /**
     * 실종자 정보 조회
     * @param queryParams(UUID roomId, String nm, String msspsnIdntfccd)
     * @return ResponseEntity<Map<String, Object>>
     */
    ResponseEntity<Map<String, Object>> getMissingPersonInfo(Map<String, Object> queryParams) throws Exception;

    /**
     * 실종자 이미지 조회
     * @param queryParams(String nm, String msspsnIdntfccd)
     * @return ResponseEntity<byte[]>
     */
    ResponseEntity<byte[]> getMissingPersonImg(Map<String, Object> queryParams) throws Exception;
}
