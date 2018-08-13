package com.testspring.library.model;

public class Book {


    private String ISBN;


    private String author;


    private String name_book;


    private int user_take;

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", author='" + author + '\'' +
                ", name_book='" + name_book + '\'' +
                ", user_take=" + user_take +
                '}';
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName_book(String name_book) {
        this.name_book = name_book;
    }

    public void setUser_take(int user_take) {
        this.user_take = user_take;
    }

    public String getISBN() {

        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getName_book() {
        return name_book;
    }

    public int getUser_take() {
        return user_take;
    }
}
