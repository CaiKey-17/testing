package com.example.chuyentrang.service;


import com.example.chuyentrang.model.Clothes;
import com.example.chuyentrang.repository.ClothesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClothesService {
    private final ClothesRepository clothesRepository;

    public ClothesService(ClothesRepository clothesRepository) {
        this.clothesRepository = clothesRepository;
    }

    public Clothes saveClothes(Clothes clothes) {
        return clothesRepository.save(clothes);
    }

    public List<Clothes> getAllClothes() {
        return clothesRepository.findAll();
    }

    public Page<Clothes> getAboutInfo(Pageable pageable) {
        return clothesRepository.findAll(pageable);
    }

    public Optional<Clothes> getClothes(int id) {
        return clothesRepository.findById(id);
    }
    public Clothes getClothesById(int id) {
        Optional<Clothes> optionalClothes = clothesRepository.findById(id);
        return optionalClothes.orElse(null);
    }
    public Page<Clothes> getUsersByIdRange(int minId, int maxId, Pageable pageable) {
        return clothesRepository.findByPriceBetween(minId, maxId, pageable);
    }
    public Page<Clothes> getAboutInfo(String keyword, Pageable pageable) {
        if (keyword != null && !keyword.isEmpty()) {
            return clothesRepository.findByNameContainingIgnoreCase(keyword, pageable);
        } else {
            return clothesRepository.findAll(pageable);
        }
    }
    public Page<Clothes> getClothesByBrandId(int brandId, Pageable pageable) {
        return clothesRepository.findByBrandId(brandId, pageable);
    }

    public Clothes updateClothes(int id, Clothes clothes) {
        if (clothesRepository.existsById(id)) {
            clothes.setId(id);
            return clothesRepository.save(clothes);
        }
        return null;
    }

    public Page<Clothes> getClothesByBrandIdAndPriceRange(int brandId, int minPrice, int maxPrice, String keyword, Pageable pageable) {
        if (keyword != null && !keyword.isEmpty()) {
            return clothesRepository.findByBrandIdAndPriceBetweenAndNameContainingIgnoreCase(brandId, minPrice, maxPrice, keyword, pageable);
        } else {
            return clothesRepository.findByBrandIdAndPriceBetween(brandId, minPrice, maxPrice, pageable);
        }
    }

    public Page<Clothes> getClothesByBrandIdAndKeyword(int brandId, String keyword, Pageable pageable) {
        if (keyword != null && !keyword.isEmpty()) {
            return clothesRepository.findByBrandIdAndNameContainingIgnoreCase(brandId, keyword, pageable);
        } else {
            return clothesRepository.findByBrandId(brandId, pageable);
        }
    }


    public void deleteClothes(int id) {
        clothesRepository.deleteById(id);
    }


    public List<Clothes> getClothesByBrandId(int brandId) {
        return clothesRepository.findByBrandId(brandId);
    }

    public Clothes updateProduct(int id, Clothes product) {
        Clothes c = clothesRepository.findById(id).get();
        c.setName(product.getName());
        c.setContent(product.getContent());
        c.setDetail(product.getDetail());
        c.setMaterial(product.getMaterial());
        c.setPrice(product.getPrice());
        c.setQuantity(product.getQuantity());
        c.setImage(product.getImage());
        c.setImagehover(product.getImagehover());
        return clothesRepository.save(c);
    }

    public Page<Clothes> getClothesByColorNameColor(String nameColor, Pageable pageable) {
        return clothesRepository.findByColorNameColor(nameColor, pageable);
    }

    public Page<Clothes> getClothesBySizeName(String sizeName, Pageable pageable) {
        return clothesRepository.findBySizeName(sizeName, pageable);
    }

    public Page<Clothes> getClothesBySizeNameAndBrandId(String sizeName, int brandId, Pageable pageable) {
        return clothesRepository.findBySizeNameAndBrandId(sizeName, brandId, pageable);
    }

    public Page<Clothes> getClothesByColorNameColorAndBrandId(String colorName, int brandId, Pageable pageable) {
        return clothesRepository.findByColorNameColorAndBrandId(colorName, brandId, pageable);
    }


    public List<Clothes> getLatestClothes() {
        return clothesRepository.findTop8ByOrderByCreatedDateDesc();
    }


}


