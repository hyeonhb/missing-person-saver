package com.example.serviceImpl;

import com.example.repository.PhotoReportRepository;
import com.example.service.PhotoReportService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoReportServiceImpl implements PhotoReportService {

    @Autowired
    private PhotoReportRepository photoReportRepository;

    @PersistenceContext
    private EntityManager entityManager;
}
