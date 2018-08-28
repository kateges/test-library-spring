package com.testspring.library.model;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookRowMapper implements RowMapper<Book> {

    public Book mapRow(ResultSet rs, int row) throws SQLException {
        Book book = new Book();
        book.setISBN(rs.getString("ISBN"));
        book.setAuthor(rs.getString("author"));
        book.setName_book(rs.getString("name_book"));
        book.setUser_take(rs.getString("user_take"));
        return book;
    }

}