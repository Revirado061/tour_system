package org.example.springboottoursystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.domain.Facility;

import java.util.List;

@Mapper
public interface FacilityMapper {
    List<Facility> findAll();
}
