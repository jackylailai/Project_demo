package org.example.entity;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tips")
public class Tip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tipId")
    private int tipId;

    @Column(name = "unitId")
    private int unitId;

    @Column(name = "contentId")
    private int contentId;

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
