package org.example.springboottoursystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.domain.Node;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
@Mapper
public interface NodeMapper {
    Node findByName(String name);

    Node findById(long l);
<<<<<<< HEAD

    List<Node> findAll();
=======
>>>>>>> d0d46300093d1196bd0f7da5ee1bae39e074e655
}
