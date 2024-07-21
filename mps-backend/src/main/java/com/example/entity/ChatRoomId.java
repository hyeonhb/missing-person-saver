package com.example.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class ChatRoomId implements Serializable {
    private Long roomId;
    private Long userId;
    private Long mpId;

    // 기본 생성자
    public ChatRoomId() {}

    // 필드를 매개변수로 받는 생성자
    public ChatRoomId(Long roomId, Long userId, Long mpId) {
        this.roomId = roomId;
        this.userId = userId;
        this.mpId = mpId;
    }

    // equals와 hashCode 메서드 구현
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoomId that = (ChatRoomId) o;
        return Objects.equals(roomId, that.roomId) && Objects.equals(userId, that.userId) && Objects.equals(mpId, that.mpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, userId, mpId);
    }

}
