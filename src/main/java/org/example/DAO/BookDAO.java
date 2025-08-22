package org.example.DAO;

import org.example.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM books;", new BeanPropertyRowMapper<>(Book.class));
    }

    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO books (title, author, year) VALUES (?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());
    }

    public Book getBookById(int id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE bookId = ?", new BeanPropertyRowMapper<>(Book.class), id).stream().findAny().orElse(null);
    }

    public void editBook(Book book, int id) {
        jdbcTemplate.update("UPDATE books SET title = ?, author = ?, year = ? WHERE bookId = ?", book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM books WHERE bookId = ?", id);
    }

    public List<Book> getBooksPersonTook(int id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE personId = ?", new BeanPropertyRowMapper<>(Book.class), id);
    }

    public void returnBook(int id) {
        jdbcTemplate.update("UPDATE books SET personId = null WHERE bookId = ?", id);
    }

    public void giveOutBook(int bookId, int personId) {
        jdbcTemplate.update("UPDATE books SET personId = ? WHERE bookId = ?;", personId, bookId);
    }
}
