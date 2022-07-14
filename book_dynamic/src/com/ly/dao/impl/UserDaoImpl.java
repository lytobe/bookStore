package com.ly.dao.impl;

import com.ly.dao.UserDao;
import com.ly.entity.User;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from t_user where username=?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());

    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from t_user where username=? and password=?";
        return queryForOne(User.class,sql,username,password);

    }
}
