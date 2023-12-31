package org.example.entity;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

@Data//會自動生成一些通用的方法，包括 getter 和 setter 方法
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long grade;
    private String username;
    private String password;
    private String oAuthKey;
    private Long studentId;
    private int studentBatch;
    private int level;
    private String ip;
    private long longDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

}
