package org.example.springboottoursystem.domain;

<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
=======
import org.springframework.stereotype.Service;

>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
public class Edge {

    private Long id;  //编号
    double length;
    double crowd; //拥挤度
    private Long startNode;
    private Long endNode;

    public Edge() {
    }

<<<<<<< HEAD
    public Edge(Long id, double length, double crowd, Long startnode, Long endnode) {
        this.id = id;
        this.length = length;
        this.crowd = crowd;
        this.startNode = startnode;
        this.endNode = endnode;
    }

=======
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
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
}