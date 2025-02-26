package com.example.chuyentrang.repository;

import com.example.chuyentrang.model.Order;
import com.example.chuyentrang.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface ProductRepositoy extends JpaRepository<Product,Integer> {
    List<Product> findByOrderId(int orderId);
}
