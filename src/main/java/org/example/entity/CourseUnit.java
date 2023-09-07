package org.example.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "units") // Adjust the table name as needed
public class CourseUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int courseId;
    private int unitId;
    private String unitName;
    private String unitSchedule;
    private String unitSubject;
    private int order;
    private String descTitle1;
    private String descContent1;
    private String descTitle2;
    private String descContent2;
    private String videoUrl;
    private String videoFormat;
    private String dfcsId;
    private int creditUnits;
    private int state;
    private long longDate;
    private long createDate;
    private long updateDate;

    @ElementCollection
    @CollectionTable(name = "unit_student_list", joinColumns = @JoinColumn(name = "unit_id"))
    private List<String> studentList;

    // Constructors, getters, setters, and other methods as needed
}


