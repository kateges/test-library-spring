package com.testspring.library.service;

import com.testspring.library.model.Book;

import java.util.List;

public interface BookService {
    public void addBook(Book book);

    public void updateBook(Book book);

    public void removeBook(String ISBN);

    public Book getBookByISBN(String ISBN);

    public List<Book> ListBooks();
}
