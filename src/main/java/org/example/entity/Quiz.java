package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data//會自動生成一些通用的方法，包括 getter 和 setter 方法
@Entity
@Table(name = "Quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quizId;
    private Long unitId;
    private String title;
    private String content;

    private Integer tofQuiz;
    private Integer essayQuiz;
    private String answer;
    private int state;
    private long longDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    // Getter and Setter 方法
}