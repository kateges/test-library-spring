package com.testspring.library.dao;

import com.testspring.library.model.User;
import com.testspring.library.model.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void addUser(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("ges_us_test");
        simpleJdbcInsert.usingGeneratedKeyColumns("user_id");
        Map<String, Object> parameters = new HashMap<String, Object>(4);
        parameters.put("user_log", user.getUser_log());
        parameters.put("user_pwd", user.getUser_pwd());
        simpleJdbcInsert.execute(parameters);
    }

    @Transactional
    public void removeUser(String log_user) {
        String sql = "delete from ges_us_test where user_log = ?";
        Object[] params = { log_user };
        int[] types = {Types.NVARCHAR};
        jdbcTemplate.update(sql, params, types);
    }

    @Transactional
    public User getUserBylog(String log_user) {
        User user = (User) jdbcTemplate.queryForObject("select * from ges_us_test where user_log = ?",
                new Object[] { log_user }, new UserRowMapper());
        return user;
    }

    @Transactional
    public boolean isExists(String log_user) {
        List<User> userList = (List<User>) jdbcTemplate.query("select * from ges_us_test where user_log = '"+log_user+"'",
                new UserRowMapper());
        if (userList.isEmpty() )
            return false;
        else
            return true;
    }

    @Transactional
    public List<User> ListUsers() {
        List<User> userList = (List<User>) jdbcTemplate.query("select * from ges_us_test",
                new UserRowMapper());
        return userList;
    }

    @Override
    public boolean isOwnerBooks(String log_user) {
        List<User> userList = (List<User>) jdbcTemplate.query("select u.* from ges_books_test b, ges_us_test u where u.user_id=b.user_take and u.user_log = '"+log_user+"'",
                new UserRowMapper());
        if ( userList.isEmpty() )
            return false;
        else
            return true;
    }
}
