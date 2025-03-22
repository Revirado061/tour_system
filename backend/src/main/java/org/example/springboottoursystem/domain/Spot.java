package org.example.springboottoursystem.domain;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Table(name = "spot")  //说明此实体类对应于数据库的spot表
@Entity  //说明此类是个实体类
@Service
public class Spot {

    /*主键name要加上这两个注解*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

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
