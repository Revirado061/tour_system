package org.example.springboottoursystem.service.serviceImpl;

import jakarta.annotation.Resource;
import org.example.springboottoursystem.domain.User;
import org.example.springboottoursystem.mapper.UserMapper;
import org.example.springboottoursystem.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User loginService(String uname, String password) {
        return userMapper.findByUnameAndPassword(uname, password);
    }

    @Override
    public User registService(User user) {
        if (userMapper.findByUname(user.getUname()) != null) {
            return null;
        }
        userMapper.insertUser(user);
        return user;
    }

    @Override
    public void deleteUser(String uname) {
        userMapper.deleteUser(uname);
    }
}