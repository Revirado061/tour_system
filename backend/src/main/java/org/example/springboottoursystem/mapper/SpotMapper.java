package org.example.springboottoursystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.domain.Spot;

import java.util.List;

@Mapper
public interface SpotMapper {  //使用Spot作为实体类，String作为主键类型
    Spot findByName(String name);

    List<Spot> findAll();
}

