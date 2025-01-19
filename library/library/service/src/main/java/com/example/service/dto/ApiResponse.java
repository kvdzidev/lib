package com.example.service.dto;

import com.example.service.dto.ApiBook;

import java.util.List;

public class ApiResponse {
    private List<ApiBook> docs;

    public List<ApiBook> getDocs() {
        return docs;
    }

    public void setDocs(List<ApiBook> docs) {
        this.docs = docs;
    }
}
