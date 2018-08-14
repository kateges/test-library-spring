package com.testspring.library.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setUser_log(rs.getString("user_log"));
        user.setUser_pwd(rs.getString("user_pwd"));
        return user;
    }
}
