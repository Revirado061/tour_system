package org.example.springboottoursystem.repository;

import org.example.springboottoursystem.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //数据库
public interface SpotDao extends JpaRepository<Spot, String> {  //使用Spot作为实体类，String作为主键类型
    Spot findByName(String name);

}

