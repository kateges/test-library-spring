package com.testspring.library.dao;

import com.testspring.library.model.Book;

import java.util.List;

public interface BookDao {
    public void addBook(Book book);

    public void updateBook(Book book);

    public void removeBook(String ISBN);

    public Book getBookByISBN(String ISBN);

    public boolean isExists(String ISBN);

    public List<Book> ListBooks();

    public void takeBook(String ISBN, String user_take);

    public void returnBook(String ISBN);
}
