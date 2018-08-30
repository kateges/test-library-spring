package com.testspring.library.controller;

import com.testspring.library.model.Book;
import com.testspring.library.model.User;
import com.testspring.library.service.BookService;
import com.testspring.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/")
public class BookController {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", bookService.ListBooks());
        logger.debug("(DEBUG) [listBooks] listBooks : "+bookService.ListBooks().size());
        return "/books";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.ListUsers());
        logger.debug("(DEBUG) [listUsers] listUsers : "+userService.ListUsers().size());
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
        book.setUser_take("");

        if (!(this.bookService.isExists(isbn))) {
            bookService.addBook(book);
            model.addAttribute("add_book_res", "1");
            model.addAttribute("listBooks", bookService.ListBooks());
            logger.debug("(DEBUG) [addBook] add_book_res : 1");
            logger.debug("(DEBUG) [addBook] isbn : "+isbn+", author : "+author+", name_book : "+name_book);
            return "/books";
        } else {
            model.addAttribute("add_book_res", "0");
            model.addAttribute("listBooks", bookService.ListBooks());
            logger.debug("(DEBUG) [addBook] add_book_res : 0");
            logger.debug("(DEBUG) [addBook] isbn : "+isbn+", author : "+author+", name_book : "+name_book);
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
            logger.debug("(DEBUG) [addBook] add_user_res : 1");
            logger.debug("(DEBUG) [addBook] login : "+login);
            return "/users";
        } else {
            model.addAttribute("add_user_res", "0");
            model.addAttribute("listUsers", userService.ListUsers());
            logger.debug("(DEBUG) [addBook] add_user_res : 0");
            logger.debug("(DEBUG) [addBook] login : "+login);
            return "/users";
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removeBook(@RequestParam(value = "isbn_del", required = true) String isbn,
                             Model model) {
        bookService.removeBook(isbn);
        model.addAttribute("del_book_res", "1");
        model.addAttribute("listBooks", bookService.ListBooks());
        logger.debug("(DEBUG) [removeBook] del_book_res : 1");
        return "/books";
    }

    @RequestMapping(value = "/take", method = RequestMethod.GET)
    public String takeBook(@RequestParam(value = "isbn_take", required = true) String isbn,
                           @RequestParam(value = "user_take", required = true) String user_take,
                             Model model) {
        bookService.takeBook(isbn, user_take);
        model.addAttribute("take_book_res", "1");
        model.addAttribute("listBooks", bookService.ListBooks());
        logger.debug("(DEBUG) [takeBook] isbn_take : "+isbn+", user_take : "+user_take);
        return "/books";
    }

    @RequestMapping(value = "/return", method = RequestMethod.GET)
    public String returnBook(@RequestParam(value = "isbn_return", required = true) String isbn,
                           Model model) {
        bookService.returnBook(isbn);
        model.addAttribute("return_book_res", "1");
        model.addAttribute("listBooks", bookService.ListBooks());
        logger.debug("(DEBUG) [returnBook] isbn_return : "+isbn);
        return "/books";
    }

    @RequestMapping(value = "/removeuser", method = RequestMethod.GET)
    public String removeUser(@RequestParam(value = "login", required = true) String login,
                             Model model) {
        if (login.equals("tomcat")) // нельзя удалять пользователя tomcat
        {
            model.addAttribute("del_user_res", "2");
            model.addAttribute("listUsers", userService.ListUsers());
            logger.debug("(DEBUG) [removeUser] fail, login : "+login);
        }
        else {
            if (!userService.isOwnerBooks(login)) {
                userService.removeUser(login);
                model.addAttribute("del_user_res", "1");
                model.addAttribute("listUsers", userService.ListUsers());
                logger.debug("(DEBUG) [removeUser] success, login : " + login);
            } else {
                model.addAttribute("del_user_res", "0");
                model.addAttribute("listUsers", userService.ListUsers());
                logger.debug("(DEBUG) [removeUser] fail, login : " + login);
            }
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
        logger.debug("(DEBUG) [editBook] isbn : "+isbn+", author : "+author+", name_book : "+name_book);
        return "/books";
    }

}
