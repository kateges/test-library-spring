package com.testspring.library.service;

import com.testspring.library.model.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);

    public void removeUser(String log_user);

    public User getUserBylog(String log_user);

    public boolean isExists(String log_user);

    public List<User> ListUsers();

    public boolean isOwnerBooks(String log_user);
}
