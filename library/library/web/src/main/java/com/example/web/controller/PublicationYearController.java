package com.example.web.controller;

import com.example.core.entity.PublicationYear;
import com.example.service.PublicationYearService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/publication-years")
public class
PublicationYearController {
    private final PublicationYearService publicationYearService;

    public PublicationYearController(PublicationYearService publicationYearService) {
        this.publicationYearService = publicationYearService;
    }

    @GetMapping
    public List<PublicationYear> getAllPublicationYears() {
        return publicationYearService.getAllPublicationYears();
    }

    @GetMapping("/{id}")
    public PublicationYear getPublicationYearById(@PathVariable Long id) {
        return publicationYearService.getPublicationYearById(id);
    }

    @PostMapping
    public PublicationYear createPublicationYear(@RequestBody PublicationYear publicationYear) {
        return publicationYearService.createPublicationYear(publicationYear);
    }

    @PutMapping("/{id}")
    public PublicationYear updatePublicationYear(@PathVariable Long id, @RequestBody PublicationYear publicationYear) {
        return publicationYearService.updatePublicationYear(id, publicationYear);
    }

    @DeleteMapping("/{id}")
    public void deletePublicationYear(@PathVariable Long id) {
        publicationYearService.deletePublicationYear(id);
    }
}
