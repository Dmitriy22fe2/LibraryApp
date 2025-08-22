package org.example.controllers;

import org.example.DAO.BookDAO;
import org.example.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/books/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "/books/new";
    }

    @PostMapping("/books/add")
    public String addBook(Book book) {
        bookDAO.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String book(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        return "/books/book";
    }

    @GetMapping("/books/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        return "/books/edit";
    }

    @PatchMapping("/books/{id}/edit")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookDAO.editBook(book, id);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/books/{id}/delete")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }
}
