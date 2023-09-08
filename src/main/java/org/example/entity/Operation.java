package org.example.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operId")
    private Integer operId;

    @Column(name = "unitId")
    private Integer unitId;

    @Column(name = "title")
    private String title;

    @Column(name = "titleCH")
    private String titleCH;

    @Column(name = "answer")
    private Integer answer;

    @Column(name = "group1")
    private Integer group1;

    @Column(name = "group2")
    private Integer group2;

    @Column(name = "group3")
    private Integer group3;

    @Column(name = "group4")
    private Integer group4;

    private int state;
    private long longDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    // Getter and Setter 方法
}

