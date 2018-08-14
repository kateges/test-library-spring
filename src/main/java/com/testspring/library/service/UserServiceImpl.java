package com.testspring.library.service;

import com.testspring.library.dao.UserDao;
import com.testspring.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {        this.userDao = userDao;    }

    @Override

    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    @Override
    public void removeUser(String log_user) {
        this.userDao.removeUser(log_user);
    }

    @Override
    public User getUserBylog(String log_user) {
        return this.userDao.getUserBylog(log_user);
    }

    @Override
    public boolean isExists(String log_user) {
        return this.userDao.isExists(log_user);
    }

    @Override

    public List<User> ListUsers() {
        return this.userDao.ListUsers();
    }

    @Override
    public boolean isOwnerBooks(String log_user) {
        return this.userDao.isOwnerBooks(log_user);
    }
}
