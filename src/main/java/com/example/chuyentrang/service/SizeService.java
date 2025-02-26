package com.example.chuyentrang.service;


import com.example.chuyentrang.model.Color;
import com.example.chuyentrang.model.Size;
import com.example.chuyentrang.repository.SizeRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SizeService {
    private SizeRepository sizeRepository;

    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }
    public Size saveSize(Size size) {
        return sizeRepository.save(size);
    }

    public List<Size> getAll(){
        return sizeRepository.findAll();
    }

    public String remove(int id){
        sizeRepository.deleteById(id);
        return "Remove success";
    }
;}
