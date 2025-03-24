package org.example.springboottoursystem.domain;

public class Node {

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


    public Node orElse(Object o) {
        return null;
    }
}
