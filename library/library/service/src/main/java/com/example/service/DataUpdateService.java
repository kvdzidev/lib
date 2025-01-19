package com.example.service;

import com.example.core.entity.Author;
import com.example.core.entity.Book;
import com.example.core.repository.AuthorRepository;
import com.example.core.repository.BookRepository;
import com.example.service.dto.OpenLibraryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataUpdateService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final RestTemplate restTemplate;

    public DataUpdateService(BookRepository bookRepository, AuthorRepository authorRepository, RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.restTemplate = restTemplate;
    }

    public void updateBooksFromApi() {
        String apiUrl = "https://openlibrary.org/search.json?q=fiction";

        try {
            OpenLibraryResponse response = restTemplate.getForObject(apiUrl, OpenLibraryResponse.class);

            if (response != null && response.getDocs() != null) {
                List<Book> books = new ArrayList<>();

                for (OpenLibraryResponse.Doc doc : response.getDocs()) {
                    String authorName = doc.getAuthorName() != null ? doc.getAuthorName() : "Unknown Author";
                    String title = doc.getTitle() != null ? doc.getTitle() : "Untitled Book";
                    String isbn = doc.getIsbn() != null ? doc.getIsbn() : "Unknown ISBN";

                    Author author = authorRepository.findByName(authorName)
                            .orElseGet(() -> authorRepository.save(new Author(authorName, "Biography not available")));

                    Book book = new Book(title, isbn, author);
                    books.add(book);
                }

                if (!books.isEmpty()) {
                    bookRepository.saveAll(books);
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to update books from API: " + e.getMessage());
        }
    }
}
