package org.example.springboottoursystem.domain;

import org.springframework.stereotype.Service;

public class Spot {

    private String name;
    private int heat;
    private double grade;
    private String type;

    public Spot() {
    }


    public Spot(String name, int heat, double grade, String type) {
        this.name = name;
        this.heat = heat;
        this.grade = grade;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
