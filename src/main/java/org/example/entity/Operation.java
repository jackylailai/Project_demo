package org.example.entity;
//schema 答對是否往下走 答錯就斷
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
@Data//會自動生成一些通用的方法，包括 getter 和 setter 方法
@Entity
@Table(name = "Operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operId;
    private Long unitId;
    private Long contentId;
    private String title;
    private String titleCH;
    private Integer answer;
    private Integer group1;
    private Integer group2;
    private Integer group3;
    private Integer group4;
    private String pictureName;
    private int state;
    private long longDate;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    public Operation() {
        this.longDate = System.currentTimeMillis();
    }
    // Getter and Setter 方法
}

