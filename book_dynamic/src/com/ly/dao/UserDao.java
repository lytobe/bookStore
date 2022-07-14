package com.ly.dao;

import com.ly.entity.User;

public interface UserDao {


    //根据用户名查询用户信息，如果返回null，说明没有这个用户
    public User queryUserByUsername(String username);
    //
    public int saveUser(User user);
    //
    public User queryUserByUsernameAndPassword(String username,String password);
}
