package com.ly.service.impl;

import com.ly.dao.UserDao;
import com.ly.dao.impl.UserDaoImpl;
import com.ly.entity.User;
import com.ly.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public void register(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
       return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean exitsUsername(String username) {
        if( userDao.queryUserByUsername(username)==null){
            return false;
        }
        return true;
    }
}
