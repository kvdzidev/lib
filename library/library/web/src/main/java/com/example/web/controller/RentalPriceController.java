package com.example.web.controller;

import com.example.core.entity.RentalPrice;
import com.example.service.RentalPriceService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rental-prices")
public class RentalPriceController {
    private final RentalPriceService rentalPriceService;

    public RentalPriceController(RentalPriceService rentalPriceService) {
        this.rentalPriceService = rentalPriceService;
    }

    @GetMapping
    public List<RentalPrice> getAllRentalPrices() {
        return rentalPriceService.getAllRentalPrices();
    }

    @GetMapping("/{id}")
    public RentalPrice getRentalPriceById(@PathVariable Long id) {
        return rentalPriceService.getRentalPriceById(id);
    }

    @PostMapping
    public RentalPrice createRentalPrice(@RequestBody RentalPrice rentalPrice) {
        return rentalPriceService.createRentalPrice(rentalPrice);
    }

    @PutMapping("/{id}")
    public RentalPrice updateRentalPrice(@PathVariable Long id, @RequestBody RentalPrice rentalPrice) {
        return rentalPriceService.updateRentalPrice(id, rentalPrice);
    }

    @DeleteMapping("/{id}")
    public void deleteRentalPrice(@PathVariable Long id) {
        rentalPriceService.deleteRentalPrice(id);
    }
}
