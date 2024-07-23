package com.example.service;

import com.example.entity.MissingPerson;

import java.util.Map;

public interface MissingPersonService {
    String getMissingPersonList(Map<String, String> queryParams);
    String getMissingPersonInfo(Map<String, String> queryParams);

    MissingPerson getMissingPerson(Long id);
}
