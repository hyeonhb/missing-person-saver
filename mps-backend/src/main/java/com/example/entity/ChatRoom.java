package com.example.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "CHAT_ROOM")
public class ChatRoom {

    @EmbeddedId
    private ChatRoomId chatRoomId;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private Users users;  // Users N:1 관계

    @MapsId("mpId")
    @ManyToOne
    @JoinColumn(name = "MP_ID", insertable = false, updatable = false)
    private MissingPerson missingPerson;  // MissingPERSON N:1 관계

    @CreationTimestamp // 날짜는 자동으로 추가
    @Column(updatable = false, name = "INS_DT")
    private LocalDateTime insDt = null; // 날짜

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> msgList = new ArrayList<>(); // ChatMessage 1:N 관계

    @OneToMany(mappedBy = "chatRoom")
    private List<PhotoReport> photoReportList = new ArrayList<>(); // PhotoReport 1:N 관계

    public ChatRoom() {}

    @Builder
    public ChatRoom(
            ChatRoomId chatRoomId,
            Users users,
            MissingPerson missingPerson,
            LocalDateTime insDt
    ) {
        this.chatRoomId = chatRoomId;
        this.users = users;
        this.missingPerson = missingPerson;
        this.insDt = insDt;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "chatRoomId=" + chatRoomId +
                ", insDt=" + insDt +
                '}';
    }
}
