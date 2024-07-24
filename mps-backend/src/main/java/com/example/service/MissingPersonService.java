package com.example.service;

import com.example.entity.MissingPerson;

import java.util.Map;

public interface MissingPersonService {
    String getMissingPersonList(Map<String, String> queryParams);
    String getMissingPersonInfo(Map<String, String> queryParams);

    /**
     * 실종자 조회
     * @param id
     * @return 추후 api로 호출해 사용할 예정으로 정보 값이 무조건 있다는 것을 전제로 함
     */
    MissingPerson getMissingPerson(Long id);
}
