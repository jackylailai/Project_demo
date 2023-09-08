package org.example.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "courses")
public class Course implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int courseId;
    private int courseType;
    private String courseName;
    private String courseSchedule;
    private String courseDesc;
    private int creditUnits;
    private int state;
    private long longDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    private String studentList;
    // Getters and setters
}


