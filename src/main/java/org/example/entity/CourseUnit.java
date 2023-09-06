package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "courseunits")
public class CourseUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer courseId;
    private Integer unitSeq;
    private String unitName;
    private String unitSchedule;
    private String unitSubject;
    private String descTitle1;
    private String descContent1;
    private String descTitle2;
    private String descContent2;
    private String videoUrl;
    private String videoFormat;
    private Integer creditUnits;

    // Getter and Setter 方法
}

