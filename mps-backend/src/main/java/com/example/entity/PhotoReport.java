package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "PHOTO_REPORT")
public class PhotoReport {

    @EmbeddedId
    private PhotoReportId id;

    @MapsId("chatRoomId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
            @JoinColumn(name = "MP_ID", referencedColumnName = "MP_ID"),
            @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
    })
    private ChatRoom chatRoom; // ChatRoom N:1 관계

    @Lob
    @Column(name = "PHOTO")
    private byte[] photo = null;

    @Column(name = "PHOTO_NAME")
    private String photoName = null; // 파일명

    @Column(name = "PHOTO_SIZE", nullable = false, columnDefinition = "long default 0") // 기본값 0으로 설정
    private Long photoSize;

    @Column(name = "PHOTO_TYPE")
    private String photoType = null;

    @CreationTimestamp // 날짜는 자동으로 추가
    @Column(updatable = false, name = "INS_DT")
    private LocalDateTime insDt = null; // 날짜

    public PhotoReport() {}

    @Override
    public String toString() {
        return "ChatMessage{" +
                "chatMessageId=" + id +
                ", insDt=" + insDt +
                '}';
    }
}
