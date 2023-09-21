package org.example.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data//會自動生成一些通用的方法，包括 getter 和 setter 方法
@Entity
@Table(name = "Unit")
public class Unit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long courseId;

    private Long unitId;
    private String unitName;
    private String unitSchedule;
    private String unitSubject;
    private int unitOrder;
    private String descTitle1;
    private String descContent1;
// 測驗情境
    private String descTitle2;
//測驗說明
    private String descContent2;
    private String descTitle3;
    private String descContent3;
    private String videoUrl;
    private String videoFormat;
    private String dfcsFilename;
    private int creditUnits;

    private String pictureUrl1;
    private String pictureUrl2;
    private String pictureUrl3;
    private String pictureUrl4;

    private int state;
    private long longDate;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "studentListId")
    private String studentListId;

    public String getName() {
        return unitName;
    }
    //先設定為id
    public Unit() {
        this.longDate = System.currentTimeMillis();
    }
    // Constructors, getters, setters, and other methods as needed
}

