package com.ly.test;

import com.ly.dao.impl.UserDaoImpl;
import com.ly.entity.User;
import org.junit.Test;

class UserDaoTest {
    UserDaoImpl userDao = new UserDaoImpl();

    @Test
    void queryUserByUsername() {
        if(userDao.queryUserByUsername("admin")==null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"小哈哈","1234","hysd@qq.com")));
    }

    @Test
    void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("小哈哈","1234")!=null){
            System.out.println("登陆成功");
        }else{
            System.out.println("用户名或密码错误");
        }
    }
}