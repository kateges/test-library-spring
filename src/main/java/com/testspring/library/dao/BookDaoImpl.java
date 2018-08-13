package com.testspring.library.dao;

import com.testspring.library.model.Book;
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

    @Transactional
    public void addBook(Book book) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
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
        int resp = jdbcTemplate.update(sql, new Object[] { book.getISBN(), book.getAuthor(),
                book.getName_book(), book.getUser_take() });
    }

    @Transactional
    public void removeBook(String ISBN) {
        int resp = jdbcTemplate.update("delete from ges_books_test where isbn = ?", ISBN);
    }


    @Transactional
    public List<Book> ListBooks() {
        List<Book> bookList = (List<Book>) jdbcTemplate.query("select * from ges_books_test",
                new BookRowMapper());
        return bookList;
    }

}
