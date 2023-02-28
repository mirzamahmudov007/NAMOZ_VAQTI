package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.data.relational.core.sql.In;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.xml.stream.Location;
import java.io.Serializable;
import java.util.List;
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


    private String oxirgiIsh;
    private Message message;
    private Long chatId;

    private String userLang;


    private String step;

    private String UserRegion;

    private Integer bugungiNamozVatiMsgID;

    private String data;

    private Double lat;

    private Double lang;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLang() {
        return lang;
    }

    public String getOxirgiIsh() {
        return oxirgiIsh;
    }

    public void setOxirgiIsh(String oxirgiIsh) {
        this.oxirgiIsh = oxirgiIsh;
    }


    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Integer getBugungiNamozVatiMsgID() {
        return bugungiNamozVatiMsgID;
    }

    public void setBugungiNamozVatiMsgID(Integer bugungiNamozVatiMsgID) {
        this.bugungiNamozVatiMsgID = bugungiNamozVatiMsgID;
    }

    public void setLang(Double lang) {
        this.lang = lang;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getUserLang() {
        return userLang;
    }

    public void setUserLang(String userLang) {
        this.userLang = userLang;
    }

    public String getUserRegion() {
        return UserRegion;
    }

    public void setUserRegion(String userRegion) {
        UserRegion = userRegion;
    }
}
