package com.example.chuyentrang.service;

import com.example.chuyentrang.model.Color;
import com.example.chuyentrang.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorSerivce {
    @Autowired
    private ColorRepository colorRepository;

    public ColorSerivce(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }
    public List<Color> getAllColor() {
        return colorRepository.findAll();
    }
    public Color findColor(int id){
        return colorRepository.findById(id).get();
    }

    public Color saveColor(Color color) {
        return colorRepository.save(color);
    }

    public String removeColor(int id) {
        if (colorRepository.existsById(id)) {
            colorRepository.deleteById(id);
            return "Deleted";
        }
        return "Brand not found";
    }

    public List<Color> getClothesByClothesId(int clothesId) {
        return colorRepository.findByClothesId(clothesId);
    }
}
