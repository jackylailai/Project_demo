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
    private String username;

    private Long courseId;

    private Long unitId;

    private String date;

    private Integer team;

    private Integer role;

    private Integer score;

    private Integer state;

    private Long longDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Long getUnitId() {
        return unitId;
    }

    public int getScore() {
        return score;
    }
    public String getUsername() {
        return username;
    }


    // Getter and Setter 方法
}

