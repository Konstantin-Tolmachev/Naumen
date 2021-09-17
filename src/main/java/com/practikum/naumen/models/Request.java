package com.practikum.naumen.models;


import javax.persistence.*;

@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "text", length = 1000)
    private String text;
    private String level, room, fromWhom, toWhom, status, fname, lname, pname, createDate, endDate;

    public Request(String level, String room, String fromWhom, String toWhom, String status, String text, String fname, String lname, String pname, String createDate, String endDate) {
        this.level = level;
        this.room = room;
        this.fromWhom = fromWhom;
        this.toWhom = toWhom;
        this.status = status;
        this.text = text;
        this.fname = fname;
        this.lname = lname;
        this.pname = pname;
        this.createDate = createDate;
        this.endDate = endDate;
    }

    public Request() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(String fromWhom) {
        this.fromWhom = fromWhom;
    }

    public String getToWhom() {
        return toWhom;
    }

    public void setToWhom(String toWhom) {
        this.toWhom = toWhom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}