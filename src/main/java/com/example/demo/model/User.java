package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.formatter.qual.Format;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.sql.In;
import org.springframework.format.annotation.DateTimeFormat;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.xml.stream.Location;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "telegram_users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private Integer bonusCount;
    @CreatedDate
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(updatable = false )
    private Date date = new Date();
    private Long members;

    private String oxirgiIsh;
    private Message message;
    private Long chatId;

    private String userLang;

    @OneToMany
    private Set<BonusUser> bonusUsers;
    private String step;

    private String UserRegion;

    private Integer bugungiNamozVatiMsgID;

    private String data;

    private Double lat;

    private Double lang;

}
