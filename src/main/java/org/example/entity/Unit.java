package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data//會自動生成一些通用的方法，包括 getter 和 setter 方法
@Entity
@Table(name = "Unit")
public class Unit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int courseId;


    private int unitId;


    private String unitName;


    private String unitSchedule;


    private String unitSubject;

    private int unitOrder;


    private String descTitle1;


    private String descContent1;
// 測驗情境

    private String descTitle2;
//測驗說明

    private String descContent2;


    private String videoUrl;


    private String videoFormat;


    private String dfcsId;


    private int creditUnits;

    private int state;
    private long longDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "studentListId")
    private String studentListId;
    //先設定為id

    // Constructors, getters, setters, and other methods as needed
}

