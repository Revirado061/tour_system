package org.example.springboottoursystem.domain;

import org.springframework.stereotype.Service;

public class Food {
    private Long id;  //编号
    private String name;
    private String kind; //菜系
    private String restaurant;  //饭店
    private int heat;
    private double grade;
    private double distance;

    public Food() {
    }

    public Food(Long id, String name, String kind, String restaurant, int heat, double grade, double distance) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.restaurant = restaurant;
        this.heat = heat;
        this.grade = grade;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
