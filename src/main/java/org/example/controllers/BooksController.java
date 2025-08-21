package org.example.controllers;

import org.example.DAO.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BooksController {

    private BookDAO bookDAO;

    @Autowired
    BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books";
    }
}
