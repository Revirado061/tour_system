package org.example.springboottoursystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.domain.User;

@Mapper
public interface UserMapper {
    User findByUname(String uname);

    User findByUnameAndPassword(String uname, String password);

    void insertUser(User user);

    void deleteUser(String uname);
}