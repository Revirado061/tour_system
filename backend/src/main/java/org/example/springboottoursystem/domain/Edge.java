package org.example.springboottoursystem.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class Edge {

    private Long id;  //编号
    double length;
    double crowd; //拥挤度
    private Long startNode;
    private Long endNode;

    public Edge() {
    }

    public Edge(Long id, double length, double crowd, Long startnode, Long endnode) {
        this.id = id;
        this.length = length;
        this.crowd = crowd;
        this.startNode = startnode;
        this.endNode = endnode;
    }

}