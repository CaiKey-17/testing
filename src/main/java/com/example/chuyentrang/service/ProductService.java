package com.example.chuyentrang.service;


import com.example.chuyentrang.model.Clothes;
import com.example.chuyentrang.model.Order;
import com.example.chuyentrang.model.Product;
import com.example.chuyentrang.repository.ProductRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepositoy  productRepositoy;

    public ProductService(ProductRepositoy productRepositoy) {
        this.productRepositoy = productRepositoy;
    }

    public List<Product> getProductsByOder(int id) {
        return productRepositoy.findByOrderId(id);
    }
}
