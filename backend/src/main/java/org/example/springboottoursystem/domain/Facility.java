package org.example.springboottoursystem.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

@Table(name = "facility")  //说明此实体类对应于数据库的diary表
@Entity  //说明此类是个实体类
@Service
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //编号
    private String category;  //类别

    private String name;  //名字
    private double x; //横坐标
    private double y;  //纵坐标

    private double distance;

    public Facility() {
    }

    public Facility(Long id, String category, String name, double x, double y, double distance) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
