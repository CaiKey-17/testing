package com.example.chuyentrang.service;

import com.example.chuyentrang.model.Order;
import com.example.chuyentrang.repository.OrderRepository;
import com.example.chuyentrang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    private final UserRepository userRepository;

    public OrderService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Order addOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> chuagiao(){
        return orderRepository.findByProcess(0);
    }

    public List<Order> dagiao(){
        return orderRepository.findByProcess(1);
    }


    public Order getOrder(int id){
        return orderRepository.findById(id).get();
    }

    public Order approveOrder(int id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setProcess(1);
        return orderRepository.save(order);
    }

    public boolean isEmailExists(String username) {
        if (userRepository.findByUsername(username) !=null){
            return true;
        }
        return false;
    }


}
