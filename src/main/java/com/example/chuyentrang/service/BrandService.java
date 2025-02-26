package com.example.chuyentrang.service;


import com.example.chuyentrang.model.Brand;
import com.example.chuyentrang.repository.BrandRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public String addBrand(Brand brand) {
        if (brand != null) {
            brandRepository.save(brand);
            return "Add new brand success";
        }
        return "Failed to add new brand";
    }

    public Optional<Brand> getBrand(int id) {
        return brandRepository.findById(id);
    }

    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }
    public Brand findById(int id) {
        return brandRepository.findById(id).orElse(null);
    }
    public String updateBrand(String newName, int id) {
        Optional<Brand> optionalBrand = getBrand(id);
        if (optionalBrand.isPresent()) {
            Brand newBrand = optionalBrand.get();
            newBrand.setName(newName);
            brandRepository.save(newBrand);
            return "Updated";
        }
        return "Brand not found";
    }

    public String removeBrand(int id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
            return "Deleted";
        }
        return "Brand not found";
    }
}
