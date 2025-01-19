package com.example.service;

import com.example.core.entity.Author;
import com.example.core.entity.Book;
import com.example.core.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookService(bookRepository);
    }

    @Test
    void shouldGetAllBooks() {
        List<Book> books = List.of(
                new Book("Title1", "1234567890123", new Author("Author1", "Biography1")),
                new Book("Title2", "9876543210987", new Author("Author2", "Biography2"))
        );
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void shouldGetBookById() {
        Book book = new Book("Title", "1234567890123", new Author("Author", "Biography"));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1L);

        assertEquals("Title", result.getTitle());
        assertEquals("1234567890123", result.getIsbn());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void shouldCreateBook() {
        Book book = new Book("New Book", "1234567890123", new Author("Author", "Biography"));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.createBook(book);

        assertEquals("New Book", result.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void shouldUpdateBook() {
        Book existingBook = new Book("Old Title", "1234567890123", new Author("Author", "Biography"));
        Book updatedBook = new Book("Updated Title", "9876543210987", new Author("Author", "Biography"));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(updatedBook);

        Book result = bookService.updateBook(1L, updatedBook);

        assertEquals("Updated Title", result.getTitle());
        assertEquals("9876543210987", result.getIsbn());
        verify(bookRepository, times(1)).save(existingBook);
    }

    @Test
    void shouldDeleteBook() {
        Book book = new Book("Title", "1234567890123", new Author("Author", "Biography"));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).delete(book);
    }
}
