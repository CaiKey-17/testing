package com.example.chuyentrang.controller;

import com.example.chuyentrang.model.Brand;
import com.example.chuyentrang.model.Clothes;
import com.example.chuyentrang.service.BrandService;
import com.example.chuyentrang.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/clothes")
public class ClothesController {

    private ClothesService clothesService;
    @Autowired
    private BrandService brandService;
    @Autowired
    public ClothesController(ClothesService clothesService) {
        this.clothesService = clothesService;
    }


    @PostMapping("/{id}")
    public String updateProduct(
            @PathVariable int id,
            @RequestParam("name") String name,
            @RequestParam("material") String material,
            @RequestParam("content") String content,
            @RequestParam("detail") String detail,
            @RequestParam("price") double price,
            @RequestParam("quantity") int quantity,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "imagehover", required = false) MultipartFile imagehover) {

        Clothes existingProduct = clothesService.getClothesById(id);

        existingProduct.setName(name);
        existingProduct.setMaterial(material);
        existingProduct.setContent(content);
        existingProduct.setDetail(detail);
        existingProduct.setPrice(price);
        existingProduct.setQuantity(quantity);

        if (image != null && !image.isEmpty()) {
            existingProduct.setImage(image.getOriginalFilename());
        } else {
            existingProduct.setImage(existingProduct.getImage());
        }

        if (imagehover != null && !imagehover.isEmpty()) {
            existingProduct.setImagehover(imagehover.getOriginalFilename());
        } else {
            existingProduct.setImagehover(existingProduct.getImagehover());
        }

        Clothes updatedProduct = clothesService.updateProduct(id, existingProduct);
        return "redirect:/sanpham";
    }



    @PostMapping
    public String createClothes(@RequestParam("name") String name,
                                 @RequestParam("material") String material,
                                 @RequestParam("detail") String detail,
                                 @RequestParam("content") String content,
                                 @RequestParam("price") Double price,
                                 @RequestParam("quantity") Integer quantity,
                                 @RequestParam("brandId") Integer brandId,
                                 @RequestParam("image") MultipartFile imageFile,
                                 @RequestParam("imagehover") MultipartFile imageFileHover) throws Exception {

        String folder = "src/main/resources/static/images/";

        if (imageFile.getSize() > 10485760 || imageFileHover.getSize() > 10485760) {
            throw new Exception("Kích thước tệp quá lớn");
        }

        String imageFileName = imageFile.getOriginalFilename();
        Path imagePath = Paths.get(folder + imageFileName);

        String imageHoverFileName = imageFileHover.getOriginalFilename();
        Path imageHoverPath = Paths.get(folder + imageHoverFileName);

        try {
            Files.write(imagePath, imageFile.getBytes());
            Files.write(imageHoverPath, imageFileHover.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Lỗi trong quá trình lưu hình ảnh: " + e.getMessage());
        }

        Brand brand = brandService.getBrand(brandId)
                .orElseThrow(() -> new IllegalArgumentException("Thương hiệu không tồn tại với ID: " + brandId));

        Clothes clothes = new Clothes(name,material,detail,content,price,quantity,imageFileName,imageHoverFileName,brand);

        clothesService.saveClothes(clothes);

        return "redirect:/sanpham";
    }





    @GetMapping
    public List<Clothes> getAllClothes() {
        return clothesService.getAllClothes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clothes> getClothesById(@PathVariable int id) {
        Clothes clothes = clothesService.getClothesById(id);
        if (clothes != null) {
            return ResponseEntity.ok(clothes);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public String deleteClothes(@PathVariable int id) {
        clothesService.deleteClothes(id);
        return "redirect:/sanpham";
    }

   

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<Clothes>> getClothesByBrandId(@PathVariable int brandId) {
        List<Clothes> clothesList = clothesService.getClothesByBrandId(brandId);
        return ResponseEntity.ok(clothesList);
    }
}
