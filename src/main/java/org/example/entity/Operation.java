package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data//會自動生成一些通用的方法，包括 getter 和 setter 方法
@Entity
@Table(name = "Operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long operId;
    private Long unitId;
    private String title;
    private String titleCH;
    private Integer answer;
    private Integer group1;
    private Integer group2;
    private Integer group3;
    private Integer group4;
    private int state;
    private long longDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    // Getter and Setter 方法
}

