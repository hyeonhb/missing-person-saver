package com.example.service;

import java.util.List;
import java.util.Map;

public interface ChatGptService {
    Map<String, Object> prompt(String msg, String base);
}
