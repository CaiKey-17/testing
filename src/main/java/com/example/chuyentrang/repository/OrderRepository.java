package com.example.chuyentrang.repository;

import com.example.chuyentrang.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByProcess(int process);
}
