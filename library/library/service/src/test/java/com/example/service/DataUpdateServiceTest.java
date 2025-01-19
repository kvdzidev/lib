package com.example.service;

import com.example.core.entity.Author;
import com.example.core.entity.Book;
import com.example.core.repository.AuthorRepository;
import com.example.core.repository.BookRepository;
import com.example.service.dto.OpenLibraryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class DataUpdateServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private RestTemplate restTemplate;

    private DataUpdateService dataUpdateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dataUpdateService = new DataUpdateService(bookRepository, authorRepository, restTemplate);
    }

    @Test
    void updateBooksFromApi_shouldHandleValidResponse() {
        OpenLibraryResponse.Doc doc = new OpenLibraryResponse.Doc();
        doc.setTitle("Test Book");
        doc.setAuthorName(Collections.singletonList("Test Author"));
        doc.setIsbn(Collections.singletonList("1234567890123"));

        OpenLibraryResponse response = new OpenLibraryResponse();
        response.setDocs(Collections.singletonList(doc));

        when(restTemplate.getForObject(any(String.class), eq(OpenLibraryResponse.class))).thenReturn(response);
        when(authorRepository.findByName(eq("Test Author"))).thenReturn(Optional.empty());
        when(authorRepository.save(any(Author.class))).thenReturn(new Author("Test Author", "Biography not available"));

        assertDoesNotThrow(() -> dataUpdateService.updateBooksFromApi());

        verify(bookRepository, times(1)).saveAll(anyList());
    }

    @Test
    void updateBooksFromApi_shouldHandleEmptyResponse() {
        OpenLibraryResponse response = new OpenLibraryResponse();
        response.setDocs(Collections.emptyList());

        when(restTemplate.getForObject(any(String.class), eq(OpenLibraryResponse.class))).thenReturn(response);

        assertDoesNotThrow(() -> dataUpdateService.updateBooksFromApi());

        verify(bookRepository, never()).saveAll(anyList());
    }

    @Test
    void updateBooksFromApi_shouldHandleException() {
        when(restTemplate.getForObject(any(String.class), eq(OpenLibraryResponse.class))).thenThrow(new RuntimeException("API error"));

        assertDoesNotThrow(() -> dataUpdateService.updateBooksFromApi());

        verify(bookRepository, never()).saveAll(anyList());
    }
}
