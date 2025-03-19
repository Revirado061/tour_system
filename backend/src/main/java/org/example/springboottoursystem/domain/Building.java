package org.example.springboottoursystem.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

@Table(name = "building")  //说明此实体类对应于数据库的diary表
@Entity  //说明此类是个实体类
@Service
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //编号
    private String name;
    private double x; //横坐标
    private double y;  //纵坐标

    public Building() {
    }

    public Building(Long id, String name, double x, double y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
