package com.example.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class PublicationYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Year cannot be null")
    private Integer year;

    private String country; // Dodaj pole country

    public PublicationYear() {
    }

    public PublicationYear(Integer year, String country) {
        this.year = year;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
