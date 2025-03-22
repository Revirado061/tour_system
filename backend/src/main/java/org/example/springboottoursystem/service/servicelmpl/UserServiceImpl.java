package org.example.springboottoursystem.service.servicelmpl;

import jakarta.annotation.Resource;
import org.example.springboottoursystem.domain.User;
import org.example.springboottoursystem.repository.UserDao;
import org.example.springboottoursystem.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;  //UserDao接口，先将上一层的接口实例化，以便调用里边的函数

    @Override
    public User loginService(String uname, String password) {  //User是自己写的一个类
        // 如果账号密码都对则返回登录的用户对象，若有一个错误则返回null
        User user = userDao.findByUnameAndPassword(uname, password);  //接口里定义了该函数
        // 重要信息置空
        if(user != null){
            user.setPassword("");
        }
        return user;
    }

    @Override
    public User registService(User user) {
        if(userDao.findByUname(user.getUname())!=null) {
            // 无法注册
            return null;
        }
        else {
            //返回创建好的用户对象(带uid)
            User newUser = userDao.save(user);
            if (newUser != null) {
                newUser.setPassword("");
            }
            return newUser;
        }
    }
}
