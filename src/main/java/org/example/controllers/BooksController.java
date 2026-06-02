package org.example.controllers;

import org.example.DAO.BookDAO;
import org.example.DAO.PersonDAO;
import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BooksController {

    private PersonDAO personDAO;
    private BookDAO bookDAO;

    @Autowired
    BooksController(BookDAO bookDAO,  PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
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
    public String book(@PathVariable("id") int id, Model model, @ModelAttribute("reader") Person reader) {
        model.addAttribute("book", bookDAO.getBookById(id));
        model.addAttribute("person", personDAO.getBookHoldingPerson(id));
        model.addAttribute("peopleList", personDAO.getPeople());
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

    @PatchMapping("/books/{id}")
    public String returnBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookDAO.returnBook(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/books/{id}/giveout")
    public String giveOutBook(@PathVariable("id") int id, @ModelAttribute("book") Book book, @ModelAttribute("person") Person person) {
        bookDAO.giveOutBook(id, person.getPersonId());
        return "redirect:/books/" + id;
    }
}
