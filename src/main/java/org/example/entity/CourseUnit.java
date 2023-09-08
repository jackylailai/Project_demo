package org.example.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Courseunits")
public class CourseUnit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "courseId")
    private int courseId;

    @Column(name = "unitId")
    private int unitId;

    @Column(name = "unitName")
    private String unitName;

    @Column(name = "unitSchedule")
    private String unitSchedule;

    @Column(name = "unitSubject")
    private String unitSubject;

    @Column(name = "order")
    private int order;

    @Column(name = "descTitle1")
    private String descTitle1;

    @Column(name = "descContent1")
    private String descContent1;
// 測驗情境
    @Column(name = "descTitle2")
    private String descTitle2;
//測驗說明
    @Column(name = "descContent2")
    private String descContent2;

    @Column(name = "videoUrl")
    private String videoUrl;

    @Column(name = "videoFormat")
    private String videoFormat;

    @Column(name = "dfcsId")
    private String dfcsId;

    @Column(name = "creditUnits")
    private int creditUnits;

    private int state;
    private long longDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "studentListId")
    private Integer studentListId;
    //先設定為id

    // Constructors, getters, setters, and other methods as needed
}

