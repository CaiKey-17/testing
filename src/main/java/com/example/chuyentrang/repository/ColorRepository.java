package com.example.chuyentrang.repository;

import com.example.chuyentrang.model.Clothes;
import com.example.chuyentrang.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color,Integer> {
    List<Color> findByClothesId(int clothesId);
}
