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
@IdClass(ChatRoomId.class)
@Table(name = "CHAT_ROOM")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long roomId;

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Id
    @Column(name = "MP_ID")
    private Long mpId;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private Users user;  // Users N:1 관계

    @MapsId
    @ManyToOne
    @JoinColumn(name = "MP_ID", insertable = false, updatable = false)
    private MissingPerson missingPerson;  // Users N:1 관계


    @CreationTimestamp // 날짜는 자동으로 추가
    @Column(updatable = false, name = "INS_DT")
    private LocalDateTime insDt = null; // 날짜

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> msgList = new ArrayList<>(); // ChatMessage 1:N 관계
}
