package org.example.springboottoursystem.repository;

import org.example.springboottoursystem.domain.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //数据库
public interface NodeDao extends JpaRepository<Node, Long> {
    Node findByName(String name);
}
