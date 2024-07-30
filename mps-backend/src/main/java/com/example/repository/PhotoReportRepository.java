package com.example.repository;

import com.example.entity.PhotoReport;
import com.example.entity.PhotoReportId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoReportRepository extends JpaRepository<PhotoReport, PhotoReportId> {
}
