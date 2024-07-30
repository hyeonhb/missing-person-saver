package com.example.dto;

import com.example.entity.ChatMessageId;

import java.time.LocalDateTime;

public class ChatMessageDTO {
    private ChatMessageId msgId;

    private Long roomId;

    private String content;

    private Long type;

    public LocalDateTime getInsDt() { return insDt; }

    public void setInsDt(LocalDateTime insDt) { this.insDt = insDt; }

    public Long getType() { return type; }

    public void setType(Long type) { this.type = type; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Long getRoomId() { return roomId; }

    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public ChatMessageId getMsgId() { return msgId; }

    public void setMsgId(ChatMessageId msgId) { this.msgId = msgId; }

    private LocalDateTime insDt;
}
