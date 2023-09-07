package org.example.entity;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;

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

    @Column(name = "longDate")
    private long longDate;

    @Column(name = "createDate")
    private long createDate;

    @Column(name = "updateDate")
    private long updateDate;

    // Getter and Setter methods
}
