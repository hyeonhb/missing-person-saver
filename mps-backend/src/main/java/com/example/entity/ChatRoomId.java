package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatRoomId implements Serializable {
    private Long roomId;
    private Long userId;
    private Long mpId;
}
