package org.example.springboottoursystem.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

@Table(name = "huffman_tree")
@Entity  //说明此类是个实体类
@Service
public class HuffmanTree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tree;  //属于第几棵树（第几篇文章）
    private char word;
    private String code;

    public HuffmanTree() {
    }

    public HuffmanTree(Long tree, char word, String code) {
        this.tree = tree;
        this.word = word;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTree() {
        return tree;
    }

    public void setTree(Long tree) {
        this.tree = tree;
    }

    public char getWord() {
        return word;
    }

    public void setWord(char word) {
        this.word = word;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
