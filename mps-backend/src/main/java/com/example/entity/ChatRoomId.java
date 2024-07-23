package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Embeddable
public class ChatRoomId implements Serializable {
    @Column(name = "ROOM_ID")
    private UUID roomId;
    private Long userId;
    private Long mpId;

    public UUID getRoomId() {
        return roomId;
    }

    @Builder
    public ChatRoomId(
        UUID roomId,
        Long userId,
        Long mpId
    ) {
        this.roomId = roomId;
        this.userId = userId;
        this.mpId = mpId;
    }
}