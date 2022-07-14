package com.ly.service;

import com.ly.entity.User;

public interface UserService {

    /**
     * 注册
     * @param user
     */
    public void register(User user);

    /**
     * 登录
     * @param user
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean exitsUsername(String username);
}
