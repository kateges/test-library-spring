package com.testspring.library.controller;

import com.testspring.library.model.Book;
import com.testspring.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.ListBooks());
        return "/books";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(@RequestParam(value = "isbn_add", required = true) String isbn,
                          @RequestParam(value = "author_add", required = true) String author,
                          @RequestParam(value = "bookname_add", required = true) String name_book,
                          Model model) {
        Book book = new Book();
        book.setISBN(isbn);
        book.setAuthor(author);
        book.setName_book(name_book);
        book.setUser_take(0);

        if (!(this.bookService.isExists(isbn))) {
            bookService.addBook(book);
            model.addAttribute("add_book_res", "1");
            model.addAttribute("listBooks", bookService.ListBooks());
            return "/books";
        } else {
            model.addAttribute("add_book_res", "0");
            model.addAttribute("listBooks", bookService.ListBooks());
            return "/books";
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removeBook(@RequestParam(value = "isbn_del", required = true) String isbn,
                             Model model) {
        System.out.print(isbn);
        bookService.removeBook(isbn);
        return "/books?remove=1";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editBook(@RequestParam(value = "isbn_edit", required = true) String isbn,
                          @RequestParam(value = "author_edit", required = true) String author,
                          @RequestParam(value = "bookname_edit", required = true) String name_book,
                          Model model) {
        Book book = new Book();
        book.setISBN(isbn);
        book.setAuthor(author);
        book.setName_book(name_book);

        bookService.updateBook(book);
        model.addAttribute("edit_book_res", "1");
        model.addAttribute("listBooks", bookService.ListBooks());
        return "/books";
    }

}
