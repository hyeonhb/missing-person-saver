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

@Table(name = "MISSING_PERSON")
public class MissingPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MP_ID")
    private Long mpId;

    @Column(name = "NAME", nullable = false, length = 10)
    private String name;

    @Column(name = "MISSING_DATE", nullable = false)
    private Integer missingDate;

    @OneToMany(mappedBy = "missingPerson")
    private List<ChatRoom> roomList = new ArrayList<>(); // ChatRoom 1:N 관계
}