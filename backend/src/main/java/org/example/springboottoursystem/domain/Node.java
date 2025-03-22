package org.example.springboottoursystem.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Table(name = "node")
@Entity  //说明此类是个实体类
@Service
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Node(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Node() {

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


}
