package com.example.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenLibraryResponse {
    private List<Doc> docs;

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Doc {
        private String title;
        private List<String> isbn;
        private List<String> author_name;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIsbn() {
            return isbn != null && !isbn.isEmpty() ? isbn.get(0) : null;
        }

        public void setIsbn(List<String> isbn) {
            this.isbn = isbn;
        }

        public String getAuthorName() {
            return author_name != null && !author_name.isEmpty() ? author_name.get(0) : null;
        }

        public void setAuthorName(List<String> author_name) {
            this.author_name = author_name;
        }
    }
}
