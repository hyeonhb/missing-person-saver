package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "CHAT_MESSAGE")
public class ChatMessage {

    @EmbeddedId
    private ChatMessageId id;

    @MapsId("chatRoomId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
            @JoinColumn(name = "MP_ID", referencedColumnName = "MP_ID"),
            @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
    })
    private ChatRoom chatRoom; // ChatRoom N:1 관계

    @Column(name = "CONTENT", length = 3000)
    private String content; // 메시지 내용

    @Column(name = "TYPE")
    private Long type; // 메시지 내용

    @CreationTimestamp // 날짜는 자동으로 추가
    @Column(updatable = false, name = "INS_DT")
    private LocalDateTime insDt = null; // 날짜

    @Override
    public String toString() {
        return "ChatMessage{" +
                "chatMessageId=" + id +
                ", insDt=" + insDt +
                '}';
    }
}
