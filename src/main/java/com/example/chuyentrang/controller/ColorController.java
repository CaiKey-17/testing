package com.example.chuyentrang.controller;

import com.example.chuyentrang.model.Brand;
import com.example.chuyentrang.model.Clothes;
import com.example.chuyentrang.model.Color;
import com.example.chuyentrang.repository.ColorRepository;
import com.example.chuyentrang.service.BrandService;
import com.example.chuyentrang.service.ClothesService;
import com.example.chuyentrang.service.ColorSerivce;
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
@RequestMapping("/colors")
public class ColorController {

    private ColorSerivce colorSerivce;

    @Autowired
    private ClothesService clothesService;
    @Autowired
    public ColorController(ColorSerivce clothesService) {
        this.colorSerivce = clothesService;
    }

    @PostMapping
    public String createClothes(@RequestParam("name") String name,
                               @RequestParam("image") MultipartFile imageFile,
                               @RequestParam("color_code") String color_code,
                               @RequestParam("name_color") String name_color,
                               @RequestParam("clothesId") Integer clothesId) throws Exception {

        String folder = "src/main/resources/static/images/";

        if (imageFile.getSize() > 10485760 ) {
            throw new Exception("Kích thước tệp quá lớn");
        }

        String imageFileName = imageFile.getOriginalFilename();
        Path imagePath = Paths.get(folder + imageFileName);



        try {
            Files.write(imagePath, imageFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Lỗi trong quá trình lưu hình ảnh: " + e.getMessage());
        }

        Clothes clothes = clothesService.getClothes(clothesId)
                .orElseThrow(() -> new IllegalArgumentException("Thương hiệu không tồn tại với ID: " + clothesId));
        Color color = new Color();
        color.setName(name);
        color.setImage(imageFileName);
        color.setColorCode(color_code);
        color.setNameColor(name_color);
        color.setClothes(clothes);

        colorSerivce.saveColor(color);
        return "redirect:/mausac";

    }
}
