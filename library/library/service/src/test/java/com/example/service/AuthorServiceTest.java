package com.example.service;

import com.example.core.entity.Author;
import com.example.core.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authorService = new AuthorService(authorRepository);
    }

    @Test
    void shouldFindAllAuthors() {
        List<Author> authors = List.of(
                new Author("Author1", "Biography1"),
                new Author("Author2", "Biography2")
        );
        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorService.findAllAuthors();

        assertEquals(2, result.size());
        assertEquals("Author1", result.get(0).getName());
        assertEquals("Biography1", result.get(0).getBiography());
        assertEquals("Author2", result.get(1).getName());
        assertEquals("Biography2", result.get(1).getBiography());
        verify(authorRepository, times(1)).findAll();
    }



    @Test
    void shouldFindAuthorById() {
        Author author = new Author("Test Author", "Biography");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author result = authorService.findAuthorById(1L);

        assertEquals("Test Author", result.getName());
        assertEquals("Biography", result.getBiography());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void shouldCreateAuthor() {
        Author author = new Author("New Author", "Biography");
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author result = authorService.createAuthor(author);

        assertEquals("New Author", result.getName());
        assertEquals("Biography", result.getBiography());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    void shouldUpdateAuthor() {
        Author existingAuthor = new Author("Old Name", "Old Biography");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(existingAuthor));

        Author updatedAuthor = new Author("Updated Name", "Updated Biography");
        when(authorRepository.save(existingAuthor)).thenReturn(updatedAuthor);

        Author result = authorService.updateAuthor(1L, updatedAuthor);

        assertEquals("Updated Name", result.getName());
        assertEquals("Updated Biography", result.getBiography());
        verify(authorRepository, times(1)).save(existingAuthor);
    }

    @Test
    void shouldDeleteAuthor() {
        Author author = new Author("Author", "Biography");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        authorService.deleteAuthor(1L);

        verify(authorRepository, times(1)).delete(author);
    }
}
