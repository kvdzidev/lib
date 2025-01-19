package com.example.service;

import com.example.core.entity.RentalPrice;
import com.example.core.repository.RentalPriceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RentalPriceService {
    private final RentalPriceRepository rentalPriceRepository;

    public RentalPriceService(RentalPriceRepository rentalPriceRepository) {
        this.rentalPriceRepository = rentalPriceRepository;
    }

    public List<RentalPrice> getAllRentalPrices() {
        return rentalPriceRepository.findAll();
    }

    public RentalPrice getRentalPriceById(Long id) {
        return rentalPriceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("RentalPrice not found with id: " + id));
    }

    public RentalPrice createRentalPrice(RentalPrice rentalPrice) {
        return rentalPriceRepository.save(rentalPrice);
    }

    public RentalPrice updateRentalPrice(Long id, RentalPrice rentalPrice) {
        RentalPrice existingRentalPrice = getRentalPriceById(id);
        existingRentalPrice.setPrice(rentalPrice.getPrice());
        existingRentalPrice.setCurrency(rentalPrice.getCurrency());
        return rentalPriceRepository.save(existingRentalPrice);
    }

    public void deleteRentalPrice(Long id) {
        RentalPrice rentalPrice = getRentalPriceById(id);
        rentalPriceRepository.delete(rentalPrice);
    }
}
