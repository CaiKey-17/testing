package com.example.chuyentrang.controller;


import com.example.chuyentrang.model.Clothes;
import com.example.chuyentrang.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.example.chuyentrang.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@WebMvcTest(HomeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClothesService clothesService;

    @MockBean
    private BrandService brandService;

    @MockBean
    private ColorSerivce colorSerivce;

    @MockBean
    private SizeService sizeService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ProductService productService;

    @MockBean
    private EmailService emailService;

    @Test
    void testHome() throws Exception {
        when(clothesService.getLatestClothes()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("aboutInfo"));
    }

    @Test
    void testPay() throws Exception {
        mockMvc.perform(get("/payment"))
                .andExpect(status().isOk())
                .andExpect(view().name("invoice"));
    }

    @Test
    void testTops() throws Exception {
        when(clothesService.getClothesByBrandIdAndKeyword(Mockito.anyInt(), Mockito.anyString(), Mockito.any()))
                .thenReturn(Mockito.mock(org.springframework.data.domain.Page.class));

        mockMvc.perform(get("/tops")
                        .param("page", "0")
                        .param("sortOrder", "asc"))
                .andExpect(status().isOk())
                .andExpect(view().name("top"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("aboutInfo", "currentPage", "totalPages"));
    }


}