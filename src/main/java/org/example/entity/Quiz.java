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

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "tofQuiz")
    private Integer tofQuiz;
    @Column(name = "essayQuiz")
    private Integer essayQuiz;
    @Column(name = "answer")
    private String answer;
    private int state;
    private long longDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    // Getter and Setter 方法
}