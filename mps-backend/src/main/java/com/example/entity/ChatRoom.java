package com.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor

@Table(name = "CHAT_ROOM")
@IdClass(ChatRoomId.class) // 복합키 설정
public class ChatRoom {
    @Id
    @Column(name = "ROOM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private Users user;  // Users N:1 관계

    @Id
    @Column(name = "MP_ID")
    private Long mpId; // 실종자 id

    @CreationTimestamp // 날짜는 자동으로 추가
    @Column(updatable = false, name = "INS_DT")
    private LocalDateTime insDt = null; // 날짜

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> msgList = new ArrayList<>(); // ChatMessage 1:N 관계
}
