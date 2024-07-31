package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Embeddable
public class PhotoReportId implements Serializable {
    @Column(name = "PHOTO_ID")
    private UUID photoId;

    private ChatRoomId chatRoomId;

    public UUID getPhotoId() { return photoId; }

    public PhotoReportId() {}

    @Builder
    public PhotoReportId(
            UUID photoId,
            ChatRoomId chatRoomId
    ) {
        this.photoId = photoId;
        this.chatRoomId = chatRoomId;
    }
}
