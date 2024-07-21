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
    private Integer mpId;

    @Column(name = "NAME", nullable = false, length = 10)
    private String name;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    @Column(name = "SEXDSTN_DSCD", nullable = false)
    private Integer sexdstnDscd;

    @Column(name = "RLS_DATE", nullable = false, length = 8)
    private String rlsDate;

    @Column(name = "RLS_PLACE", length = 30)
    private String rlsPlace;

    @Column(name = "HEIGHT")
    private Integer height;

    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "FRM_DSCD", length = 10)
    private String frmDscd;

    @Column(name = "FACE_SHAPE_DSCD", length = 10)
    private String faceShapeDscd;

    @Column(name = "HAIR_COLOR_DSCD", length = 10)
    private String hairColorDscd;

    @Column(name = "HAIR_SHAPE_DSCD", length = 10)
    private String hairShapeDscd;

    @Column(name = "DRESSING_DSCD", length = 10)
    private String dressingDscd;
}