package org.example.springboottoursystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.domain.Building;

import java.util.List;

@Mapper
public interface BuildingMapper {
    Building findByName(String buildingName);

    List<Building> findAll();
}
