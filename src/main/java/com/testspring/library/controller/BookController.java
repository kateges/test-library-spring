package com.testspring.library.controller;

import com.testspring.library.model.Book;
import com.testspring.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/Books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
        if (book.getISBN() == null)
            this.bookService.addBook(book);

        else
            this.bookService.updateBook(book);

        return "redirect:/books";
    }

    @RequestMapping("/remove/{isbn}")
    public String removeBook(@PathVariable("isbn") String ISBN) {
        this.bookService.removeBook(ISBN);
        return "redirect:/books";
    }

    @RequestMapping("/edit/{isbn}")
    public String editBook(@PathVariable("isbn") String ISBN, Model model) {
        model.addAttribute("book", this.bookService.getBookByISBN(ISBN));
        model.addAttribute("listBooks", this.bookService.ListBooks());
        return "/books";
    }

    @RequestMapping("bookdata/{isbn}")
    public String bookData(@PathVariable("isbn") String ISBN, Model model) {
        model.addAttribute("book", this.bookService.getBookByISBN(ISBN));
        return "/bookdata";
    }
}
