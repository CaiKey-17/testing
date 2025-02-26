package com.example.chuyentrang.controller;

import com.example.chuyentrang.model.Clothes;
import com.example.chuyentrang.model.Color;
import com.example.chuyentrang.model.Size;
import com.example.chuyentrang.service.ClothesService;
import com.example.chuyentrang.service.ColorSerivce;
import com.example.chuyentrang.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/sizes")
public class SizeController {
    private SizeService sizeService;

    @Autowired
    private ClothesService clothesService;
    @Autowired
    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @PostMapping
    public String createClothes(@RequestParam("name") String name,
                               @RequestParam("clothesId") Integer clothesId) throws Exception {

        Clothes clothes = clothesService.getClothes(clothesId)
                .orElseThrow(() -> new IllegalArgumentException("Thương hiệu không tồn tại với ID: " + clothesId));
        Size size = new Size();
        size.setName(name);
        size.setClothes(clothes);
        sizeService.saveSize(size);
        return "redirect:/kichco";

    }
}
