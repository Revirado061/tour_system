package org.example.springboottoursystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.domain.Edge;

import java.util.List;

@Mapper
public interface EdgeMapper {
    List<Edge> findAll();
}