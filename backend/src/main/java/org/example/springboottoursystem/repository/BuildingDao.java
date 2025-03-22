package org.example.springboottoursystem.repository;

import org.example.springboottoursystem.domain.Building;
import org.example.springboottoursystem.domain.Edge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //数据库
public interface BuildingDao extends JpaRepository<Building, Long> {
    Building findByName(String buildingName);
}
