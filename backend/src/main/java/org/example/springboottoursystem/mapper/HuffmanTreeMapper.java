package org.example.springboottoursystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.domain.HuffmanTree;

import java.util.List;

@Mapper
public interface HuffmanTreeMapper {
    List<HuffmanTree> findByTree(Long id);

    void save(HuffmanTree huffmanTree);
}
