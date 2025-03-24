package org.example.springboottoursystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.domain.Food;

import java.util.List;

@Mapper
public interface FoodMapper {
    List<Food> findAll();
}
