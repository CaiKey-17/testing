package com.example.chuyentrang.model;


import com.example.chuyentrang.service.BrandService;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="Clothes")
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String material;

    private String content;
    private String detail;
    private Double price;
    private int quantity;
    private String image;
    private String imagehover;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Clothes(String name, String material, String content, String detail, Double price, int quantity, String image, String imagehover, Brand brand) {
        this.name = name;
        this.material = material;
        this.content = content;
        this.detail = detail;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.imagehover = imagehover;
        this.brand = brand;
        this.createdDate = LocalDateTime.now();
    }
    public Clothes(int id, String name, Brand brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @OneToMany(mappedBy = "clothes", cascade = CascadeType.ALL)
    private List<Color> colors;

    @OneToMany(mappedBy = "clothes", cascade = CascadeType.ALL)
    private List<Size> sizes;


    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }



    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }
    public Clothes() {
    }


    public void setBrandId(int brandId, BrandService brandService) {
        this.brand = brandService.getBrand(brandId).get();
    }

    public Clothes(int id, String name, String material, String content, String detail, Double price, int quantity, String image, String imagehover, Brand brand) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.content = content;
        this.detail = detail;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.imagehover = imagehover;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImagehover() {
        return imagehover;
    }

    public void setImagehover(String imagehover) {
        this.imagehover = imagehover;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
