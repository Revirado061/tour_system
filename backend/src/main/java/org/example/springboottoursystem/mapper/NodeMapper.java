package org.example.springboottoursystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.domain.Node;

import java.util.List;

@Mapper
public interface NodeMapper {
    Node findByName(String name);

    Node findById(long l);

    List<Node> findAll();
}
