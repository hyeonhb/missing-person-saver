package com.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor

@Table(name = "CHAT_MESSAGE")
public class ChatMessage {
    @Id
    @Column(name = "MSG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long msgId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID"),
            @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
            @JoinColumn(name = "MP_ID", referencedColumnName = "MP_ID")
    })
    private ChatRoom chatRoom; // ChatRoom N:1 관계

    @Column(name = "CONTENT", length = 3000)
    private String content; // 메시지 내용

    @Column(name = "TYPE")
    private Long type; // 메시지 내용

    @CreationTimestamp // 날짜는 자동으로 추가
    @Column(updatable = false, name = "INS_DT")
    private LocalDateTime insDt = null; // 날짜
}