package com.example.web.controller;

import com.example.core.entity.Book;
import com.example.service.BookService;
import com.example.service.DataUpdateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final DataUpdateService dataUpdateService;

    public BookController(BookService bookService, DataUpdateService dataUpdateService) {
        this.bookService = bookService;
        this.dataUpdateService = dataUpdateService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/update")
    public String updateBooksFromApi() {
        dataUpdateService.updateBooksFromApi();
        return "Books updated successfully from the API!";
    }
}
