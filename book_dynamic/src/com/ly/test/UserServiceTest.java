package com.ly.test;


import com.ly.entity.User;
import com.ly.service.UserService;
import com.ly.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {
   UserService userService=new UserServiceImpl();

    @Test
    public void register() {
        userService.register(new User(null,"博雅","1234","null"));
    }

    @Test
    public void login() {
        userService.login(new User(null,"博雅","1234","null"));
        System.out.println(userService.login(new User(null,"博雅","1234","null")));
    }

    @Test
    public void exitsUsername() {
        if(userService.exitsUsername("小哈哈")){
            System.out.println("用户名已存在");
        }
        else{
            System.out.println("用户名可用");
        }
    }
}