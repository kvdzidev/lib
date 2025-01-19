package com.example.service;

import com.example.core.entity.PublicationYear;
import com.example.core.repository.PublicationYearRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PublicationYearService {
    private final PublicationYearRepository publicationYearRepository;

    public PublicationYearService(PublicationYearRepository publicationYearRepository) {
        this.publicationYearRepository = publicationYearRepository;
    }

    public List<PublicationYear> getAllPublicationYears() {
        return publicationYearRepository.findAll();
    }

    public PublicationYear getPublicationYearById(Long id) {
        return publicationYearRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("PublicationYear not found with id: " + id));
    }

    public PublicationYear createPublicationYear(PublicationYear publicationYear) {
        return publicationYearRepository.save(publicationYear);
    }

    public PublicationYear updatePublicationYear(Long id, PublicationYear publicationYear) {
        PublicationYear existingPublicationYear = getPublicationYearById(id);
        existingPublicationYear.setYear(publicationYear.getYear());
        existingPublicationYear.setCountry(publicationYear.getCountry());
        return publicationYearRepository.save(existingPublicationYear);
    }

    public void deletePublicationYear(Long id) {
        PublicationYear publicationYear = getPublicationYearById(id);
        publicationYearRepository.delete(publicationYear);
    }
}
