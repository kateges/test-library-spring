package com.testspring.library.dao;

import com.testspring.library.model.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.testspring.library.model.BookRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.annotation.Transactional;

public class BookDaoImpl implements BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public Book getBookByISBN(String ISBN) {
        Book book = (Book) jdbcTemplate.queryForObject("select * from ges_books_test where ISBN = ?",
                new Object[] { ISBN }, new BookRowMapper());
        return book;
    }

    @Override
    public boolean isExists(String ISBN) {
        List<Book> bookList = (List<Book>) jdbcTemplate.query("select * from ges_books_test where ISBN = '"+ISBN+"'",
                new BookRowMapper());
        if ( bookList.isEmpty() )
            return false;
        else
            return true;
    }

    @Transactional
    public void addBook(Book book) {
        jdbcTemplate.update("insert into ges_books_test (ISBN, author, name_book, user_take) values (?, ?, ?, ?)",
                    book.getISBN(), book.getAuthor(), book.getName_book(), book.getUser_take());
    }


    @Transactional
    public void updateBook(Book book) {
        String sql = "update ges_books_test set author = ?, name_book = ? where isbn = ?";
        jdbcTemplate.update(sql, new Object[] { book.getAuthor(), book.getName_book(), book.getISBN()});
    }

    @Transactional
    public void removeBook(String ISBN) {
        String sql = "delete from ges_books_test where isbn = ?";
        Object[] params = { ISBN };
        int[] types = {Types.NVARCHAR};
        jdbcTemplate.update(sql, params, types);
    }


    @Transactional
    public List<Book> ListBooks() {
        List<Book> bookList = (List<Book>) jdbcTemplate.query("select * from ges_books_test",
                new BookRowMapper());

        return bookList;
    }

    @Override
    public void takeBook(String ISBN, String user_take) {
        String sql = "update ges_books_test set user_take = ? where isbn = ?";
        Object[] params = {user_take, ISBN};
        int[] types = {Types.NVARCHAR, Types.NVARCHAR};
        jdbcTemplate.update(sql, params, types);
    }

    @Override
    public void returnBook(String ISBN) {
        String sql = "update ges_books_test set user_take = '' where isbn = ?";
        Object[] params = {ISBN};
        int[] types = {Types.NVARCHAR};
        jdbcTemplate.update(sql, params, types);
    }

}
