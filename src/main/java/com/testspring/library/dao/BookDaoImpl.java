package com.testspring.library.dao;

import com.testspring.library.model.Book;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.testspring.library.model.BookRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("ges_books_test");
        Map<String, Object> parameters = new HashMap<String, Object>(4);
        parameters.put("ISBN", book.getISBN());
        parameters.put("author", book.getAuthor());
        parameters.put("name_book", book.getName_book());
        parameters.put("user_take", book.getUser_take());
        simpleJdbcInsert.execute(parameters);
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
        System.out.print(ISBN);
    }


    @Transactional
    public List<Book> ListBooks() {
        List<Book> bookList = (List<Book>) jdbcTemplate.query("select * from ges_books_test",
                new BookRowMapper());
        return bookList;
    }

}
