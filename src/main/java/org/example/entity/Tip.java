package org.example.entity;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
@Data//會自動生成一些通用的方法，包括 getter 和 setter 方法
@Entity
@Table(name = "tip")
public class Tip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipId")
    private Long tipId;

    @Column(name = "unitId")
    private Long unitId;

    @Column(name = "contentId")
    private Long contentId;

    private String title;

    private String content;

    private int state;
    private long longDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    // Getter and Setter methods
}
