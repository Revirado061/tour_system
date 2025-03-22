package org.example.springboottoursystem.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

@Entity  //说明此类是个实体类
@Service
public class HuffmanNode{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public char character;  //只对叶子节点有用
    public int frequency;
    @OneToOne
    public HuffmanNode left;
    @OneToOne
    public HuffmanNode right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public HuffmanNode(char character, int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public HuffmanNode() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }
}
