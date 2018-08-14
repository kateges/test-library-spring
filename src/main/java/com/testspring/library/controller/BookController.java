package com.testspring.library.controller;

import com.testspring.library.model.Book;
import com.testspring.library.model.User;
import com.testspring.library.service.BookService;
import com.testspring.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", bookService.ListBooks());
        return "/books";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.ListUsers());
        return "/users";
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

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addUser(@RequestParam(value = "login_add", required = true) String login,
                          @RequestParam(value = "pwd_add", required = true) String pwd,
                          Model model) {
        User user = new User();
        user.setUser_log(login);
        user.setUser_pwd(pwd);

        if (!(this.userService.isExists(login))) {
            userService.addUser(user);
            model.addAttribute("add_user_res", "1");
            model.addAttribute("listUsers", userService.ListUsers());
            return "/users";
        } else {
            model.addAttribute("add_user_res", "0");
            model.addAttribute("listUsers", userService.ListUsers());
            return "/users";
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removeBook(@RequestParam(value = "isbn_del", required = true) String isbn,
                             Model model) {
        bookService.removeBook(isbn);
        model.addAttribute("del_book_res", "1");
        model.addAttribute("listBooks", bookService.ListBooks());
        return "/books";
    }

    @RequestMapping(value = "/removeuser", method = RequestMethod.GET)
    public String removeUser(@RequestParam(value = "login", required = true) String login,
                             Model model) {
        if (!userService.isOwnerBooks(login))
        {
            userService.removeUser(login);
            model.addAttribute("del_user_res", "1");
            model.addAttribute("listUsers", userService.ListUsers());
        }
        else
        {
            model.addAttribute("del_user_res", "0");
            model.addAttribute("listUsers", userService.ListUsers());
        }
        return "/users";
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
