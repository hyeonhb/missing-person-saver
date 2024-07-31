package com.example.dto;

import com.example.entity.PhotoReport;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PhotoReportDTO {
    private byte[] photo;
    private String photoName;
    private Long photoSize;
    private String photoType;
    private LocalDateTime insDt;

    public PhotoReportDTO(){}

    public PhotoReportDTO setDto(PhotoReport photoReport) {
        PhotoReportDTO photoReportDTO = new PhotoReportDTO();
        photoReportDTO.setPhoto(photoReport.getPhoto());
        photoReportDTO.setPhotoName(photoReport.getPhotoName());
        photoReportDTO.setPhotoSize(photoReport.getPhotoSize());
        photoReportDTO.setPhotoType(photoReport.getPhotoType());
        photoReportDTO.setInsDt(photoReport.getInsDt());

        return photoReportDTO;
    }
}
