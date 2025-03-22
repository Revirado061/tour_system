package org.example.springboottoursystem.repository;

import org.example.springboottoursystem.domain.Facility;
import org.example.springboottoursystem.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //数据库
public interface FoodDao extends JpaRepository<Food, Long> {
}
