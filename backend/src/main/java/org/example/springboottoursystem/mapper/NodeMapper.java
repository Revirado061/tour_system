package org.example.springboottoursystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.domain.Node;

@Mapper
public interface NodeMapper {
    Node findByName(String name);

    Node findById(long l);
}
