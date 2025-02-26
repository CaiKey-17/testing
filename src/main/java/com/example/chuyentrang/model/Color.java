package com.example.chuyentrang.model;

import jakarta.persistence.*;

@Entity
@Table(name="Color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String image;
    private String colorCode;
    private String nameColor;
    @ManyToOne
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    public Color() {
    }

    public Color(String name, String image, Clothes clothes) {
        this.name = name;
        this.image = image;
        this.clothes = clothes;
    }
    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
    public Color(int id, String name, String image, Clothes clothes) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.clothes = clothes;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }
}
