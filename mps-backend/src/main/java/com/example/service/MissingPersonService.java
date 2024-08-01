package com.example.service;

import com.example.entity.MissingPerson;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MissingPersonService {
    /**
     * 실종자 조회
     * @param id
     * @return MissingPerson
     */
    MissingPerson getMissingPerson(Long id);

    /**
     * 실종자 정보 조회(단일)
     * @param queryParams(UUID roomId, String nm, String msspsnIdntfccd)
     * @return ResponseEntity<Map<String, Object>>
     */
    ResponseEntity<Map<String, Object>> getMissingPersonInfo(Map<String, Object> queryParams) throws Exception;

    /**
     * 실종자 전체 조회(100개)
     * @param queryParams
     * @return
     * @throws Exception
     */
    List<ResponseEntity<Map<String, Object>>> getMissingPersonInfos(Map<String, Object> queryParams) throws Exception;


    /**
     * 실종자 이미지 조회
     * @param queryParams(String nm, String msspsnIdntfccd)
     * @return ResponseEntity<byte[]>
     */
    ResponseEntity<byte[]> getMissingPersonImg(Map<String, Object> queryParams) throws Exception;

    ResponseEntity<Map<String, Object>> getMissingPersonReportText(Long mpId) throws Exception;

    String photoAnalyzeByMissingPerson(UUID roomId);
}
