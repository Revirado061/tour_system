package org.example.springboottoursystem.domain;

import org.springframework.stereotype.Service;

public class Diary {

    private Long id;  //编号
    private String title;  //标题
    private String spot;  //景点
    private String author;  //作者
    private String date;  //发表日期
    private int heat;  //热度
    private double grade;  //评分
    private String text;  //内容


    public Diary() {
    }

    public Diary(String title, String spot, String author, String date, int heat, double grade, String text) {
        this.title = title;
        this.spot = spot;
        this.author = author;
        this.date = date;
        this.heat = heat;
        this.grade = grade;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
