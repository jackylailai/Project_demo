package org.example.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "Attendance")
public class Attendance implements Serializable {
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

    @Column(name = "team")
    private Integer team;

    @Column(name = "role")
    private Integer role;

    @Column(name = "score")
    private Integer score;

    @Column(name = "state")
    private Integer state;

    @Column(name = "longDate")
    private Long longDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;


    // Getter and Setter 方法
}

