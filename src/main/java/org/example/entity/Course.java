package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
@Entity
@Table(name = "courses") // 必須與table名字一樣
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "courseID")
    private String courseID;

    @Column(name = "courseName")
    private String courseName;

    @Column(name = "courseDesc")
    private String courseDesc;

    @Column(name = "creditUnit")
    private Integer creditUnit;

    // Getter and Setter 方法
}

