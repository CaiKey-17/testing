package com.example.chuyentrang.model;

import jakarta.persistence.*;

@Entity
@Table(name="Size")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    public Size() {
    }

    public Size(int id, String name, Clothes clothes) {
        this.id = id;
        this.name = name;
        this.clothes = clothes;
    }

    public Size(String name, Clothes clothes) {
        this.name = name;
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

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }
}
