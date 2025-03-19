package org.example.springboottoursystem.controller;

import jakarta.annotation.Resource;
import org.example.springboottoursystem.domain.User;
import org.example.springboottoursystem.service.UserService;
import org.example.springboottoursystem.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")  //是这个控制器类的基路由
public class UserController {
    @Resource
    private UserService userService;  //先将上一层的接口实例化，以便调用里边的函数

    @PostMapping("/login")  //表示处理post请求，路由为/user/login
    public Result<User> loginController(String uname, String password){  //对应UserService里的loginService
        User user = userService.loginService(uname, password);
        if(user!=null){
            return Result.success(user,"登录成功！");
        }else{
            return Result.error("123","账号或密码错误！");
        }
    }

    @PostMapping("/register")
    public Result<User> registController(@RequestBody User newUser){
        User user = userService.registService(newUser);
        if(user!=null){
            return Result.success(user,"注册成功！");
        }else{
            return Result.error("456","用户名已存在！");
        }
    }
}
