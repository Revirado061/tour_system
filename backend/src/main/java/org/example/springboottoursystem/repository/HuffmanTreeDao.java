package org.example.springboottoursystem.repository;

import org.example.springboottoursystem.domain.HuffmanTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  //数据库
public interface HuffmanTreeDao extends JpaRepository<HuffmanTree, Long> {
    List<HuffmanTree> findByTree(Long id);
}
