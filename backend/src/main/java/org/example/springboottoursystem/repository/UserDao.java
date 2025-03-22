package org.example.springboottoursystem.repository;

import org.example.springboottoursystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //数据库
public interface UserDao extends JpaRepository<User, Long> {  //jpa能完成对数据库的映射，不需要SQL语句
    User findByUname(String uname); //通过用户名uname查找用户，注意要按照JPA的格式使用驼峰命名法
    User findByUnameAndPassword(String uname, String password);//通过用户名uname和密码查找用户
}
