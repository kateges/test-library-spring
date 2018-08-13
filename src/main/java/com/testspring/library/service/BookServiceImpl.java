package com.testspring.library.service;

import com.testspring.library.dao.BookDao;
import com.testspring.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override

    public void addBook(Book book) {
        this.bookDao.addBook(book);
    }

    @Override

    public void updateBook(Book book) {
        this.bookDao.updateBook(book);
    }

    @Override

    public void removeBook(String ISBN) {
        this.bookDao.removeBook(ISBN);
    }

    @Override

    public Book getBookByISBN(String ISBN) {
        return this.bookDao.getBookByISBN(ISBN);
    }

    @Override

    public List<Book> ListBooks() {
        return this.bookDao.ListBooks();
    }
}
