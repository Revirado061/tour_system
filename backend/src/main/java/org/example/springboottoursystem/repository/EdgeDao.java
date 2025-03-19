package org.example.springboottoursystem.repository;

import org.example.springboottoursystem.domain.Edge;
import org.example.springboottoursystem.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //数据库
public interface EdgeDao extends JpaRepository<Edge, Long> {
}