package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "QuestionAnswer")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qaSeq")
    private Integer qaSeq;

    @Column(name = "unitSeq")
    private Integer unitSeq;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "score")
    private Integer score;

    // Getter and Setter 方法
}
