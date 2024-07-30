package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Embeddable
public class ChatMessageId  implements Serializable {
    @Column(name = "MSG_ID")
    private UUID msgId;

    private ChatRoomId chatRoomId;

    public UUID getMsgId() { return msgId; }

    public ChatMessageId() {}

    @Builder
    public ChatMessageId(
            UUID msgId,
            ChatRoomId chatRoomId
    ) {
        this.msgId = msgId;
        this.chatRoomId = chatRoomId;
    }
}
