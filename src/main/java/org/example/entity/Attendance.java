package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private Integer username;

    @Column(name = "courseId")
    private Integer courseId;

    @Column(name = "unitId")
    private Integer unitId;

    @Column(name = "date")
    private String date;

    @Column(name = "group")
    private Integer group;

    @Column(name = "role")
    private Integer role;

    @Column(name = "score")
    private Integer score;

    @Column(name = "state")
    private Integer state;

    @Column(name = "longDate")
    private Long longDate;

    @Column(name = "createDate")
    private Long createDate;

    @Column(name = "updateDate")
    private Long updateDate;

    // Getter and Setter 方法
}

