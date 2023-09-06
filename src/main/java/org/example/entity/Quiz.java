package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quizSeq")
    private Integer quizSeq;

    @Column(name = "unitSeq")
    private Integer unitSeq;

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
    @Column(name = "state")
    private Integer state;
    @Column(name = "longDate")
    private long longDate;
    @Column(name = "createDate")
    private long createDate;
    @Column(name = "updateDate")
    private long updateDate;

    // Getter and Setter 方法
}