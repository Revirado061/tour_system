package org.example.springboottoursystem.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

@Table(name = "edge")
@Entity  //说明此类是个实体类
@Service
public class Edge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //编号
    double length;
    double crowd; //拥挤度
    private Long startNode;
    private Long endNode;

    public Edge() {
    }

    public Edge(Long id, double length, double crowd, Long startNode, Long endNode) {
        this.id = id;
        this.length = length;
        this.crowd = crowd;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getCrowd() {
        return crowd;
    }

    public void setCrowd(double crowd) {
        this.crowd = crowd;
    }

    public Long getStartNode() {
        return startNode;
    }

    public void setStartNode(Long startNode) {
        this.startNode = startNode;
    }

    public Long getEndNode() {
        return endNode;
    }

    public void setEndNode(Long endNode) {
        this.endNode = endNode;
    }
}