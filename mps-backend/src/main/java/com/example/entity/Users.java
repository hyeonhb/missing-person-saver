package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor

@Table(name = "USERS")
public class Users {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TELNO", nullable = false, length = 15)
    private String telno;  // 전화번호

    @Column(name = "NAME", nullable = false, length = 10)
    private String name;  // 이름

    @OneToMany(mappedBy = "userId")
    private List<ChatRoom> roomList = new ArrayList<>(); // ChatRoom 1:N 관계
}