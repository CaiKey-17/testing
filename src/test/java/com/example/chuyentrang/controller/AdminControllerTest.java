package com.example.chuyentrang.controller;

import com.example.chuyentrang.model.*;
import com.example.chuyentrang.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AdminControllerTest {

    private MockMvc mockMvc;

    // Mocking service beans
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

    @MockBean
    private ClothesService clothesService;

    @BeforeEach
    public void setUp() {
        // Initialize MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(new AdminController(
                brandService,
                colorSerivce,
                sizeService,
                orderService,
                productService,
                emailService,
                clothesService
        )).build();
    }



    @Test
    public void testAdminBrandPage() throws Exception {
        List<Brand> brands = Arrays.asList(new Brand("Brand A", 1), new Brand("Brand B", 2));
        when(brandService.getAllBrand()).thenReturn(brands);

        mockMvc.perform(get("/loaisanpham"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin_brand"))
                .andExpect(model().attribute("list_brand", brands));
    }

    @Test
    public void testAdminClothesPage() throws Exception {
        Brand brand = new Brand("Short", 1);
        List<Clothes> clothes = Arrays.asList(new Clothes(1, "Clothes A", brand), new Clothes(2, "Clothes B", brand));
        when(clothesService.getAllClothes()).thenReturn(clothes);

        mockMvc.perform(get("/sanpham"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin_clothes"))
                .andExpect(model().attribute("list_clothes", clothes));
    }

    @Test
    public void testApproveOrder() throws Exception {
        Order order = new Order();
        order.setId(1);
        order.setEmail("test@example.com");
        order.setName("Test User");

        when(orderService.approveOrder(1)).thenReturn(order);

        mockMvc.perform(put("/phe_duyet/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin_donhang"));

        verify(emailService, times(1)).sendInvoiceEmail(anyString(), anyString(), anyString());
    }

    @Test
    public void testViewOrder() throws Exception {
        List<Product> products = Arrays.asList(new Product(1, "Product A"), new Product(2, "Product B"));
        when(productService.getProductsByOder(1)).thenReturn(products);

        mockMvc.perform(get("/xem_donhang/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("chitiet_donhang"))
                .andExpect(model().attribute("order", products));
    }





    @Test
    public void testDeleteClothes() throws Exception {
        doNothing().when(clothesService).deleteClothes(1);

        mockMvc.perform(delete("/xoa_clothes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin_clothes"));

        verify(clothesService, times(1)).deleteClothes(1);
    }


}
