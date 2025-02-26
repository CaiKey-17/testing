package com.example.chuyentrang.controller;

import com.example.chuyentrang.model.*;
import com.example.chuyentrang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller()
public class HomeController {
    @Autowired
    private final BrandService brandService;


    @Autowired
    private final ColorSerivce colorSerivce;

    @Autowired
    private final SizeService sizeService;

    @Autowired
    private final OrderService orderService;


    @Autowired
    private final ProductService productService;

    @Autowired
    private final EmailService emailService;

    public HomeController(BrandService brandService, ColorSerivce colorSerivce, SizeService sizeService, OrderService orderService, ProductService productService, EmailService emailService) {
        this.brandService = brandService;
        this.colorSerivce = colorSerivce;
        this.sizeService = sizeService;
        this.orderService = orderService;
        this.productService = productService;
        this.emailService = emailService;
    }




    @GetMapping("/payment")
    public String pay() {
        return "invoice";
    }




    @GetMapping("/")
    public String home(
            Model model
    ) {
        List<Clothes> userPage = clothesService.getLatestClothes();
        model.addAttribute("aboutInfo", userPage);
        return "home";
    }


    @Autowired
    private ClothesService clothesService;


    @GetMapping("/tops")
    public String tops(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "") String priceRange,
            @RequestParam(defaultValue = "") String color,
            @RequestParam(defaultValue = "") String size
    ) {
        int brandId = 1;
        int pageSize = 4;
        Pageable pageable = sortOrder.equals("asc") ?
                PageRequest.of(page, pageSize, Sort.by("price").ascending()) :
                PageRequest.of(page, pageSize, Sort.by("price").descending());

        Page<Clothes> userPage;

        if (!size.isEmpty()) {
            userPage = clothesService.getClothesBySizeNameAndBrandId(size, brandId, pageable);
            color = "";
            priceRange = "";
            keyword = "";
        }
        else if (!color.isEmpty()) {
            userPage = clothesService.getClothesByColorNameColorAndBrandId(color, brandId, pageable);
            size = "";
            priceRange = "";
            keyword = "";
        }
        else if (!priceRange.isEmpty()) {
            switch (priceRange) {
                case "under500k":
                    userPage = clothesService.getClothesByBrandIdAndPriceRange(brandId, 0, 500000, keyword, pageable);
                    break;
                case "under1m":
                    userPage = clothesService.getClothesByBrandIdAndPriceRange(brandId, 0, 1000000, keyword, pageable);
                    break;
                case "above1m":
                    userPage = clothesService.getClothesByBrandIdAndPriceRange(brandId, 1000000, Integer.MAX_VALUE, keyword, pageable);
                    break;
                default:
                    userPage = clothesService.getClothesByBrandIdAndKeyword(brandId, keyword, pageable);
            }
            size = "";
            color = "";
            keyword = "";
        }
        else {
            userPage = clothesService.getClothesByBrandIdAndKeyword(brandId, keyword, pageable);
        }

        model.addAttribute("aboutInfo", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("priceRange", priceRange);
        model.addAttribute("brandId", brandId);
        model.addAttribute("color", color);
        model.addAttribute("size", size);

        return "top";
    }


    @GetMapping("/bottoms")
    public String bottoms(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "") String priceRange,
            @RequestParam(defaultValue = "") String color,
            @RequestParam(defaultValue = "") String size
    ) {
        int brandId = 2;
        int pageSize = 4;
        Pageable pageable = sortOrder.equals("asc") ?
                PageRequest.of(page, pageSize, Sort.by("price").ascending()) :
                PageRequest.of(page, pageSize, Sort.by("price").descending());

        Page<Clothes> userPage;

        if (!size.isEmpty()) {
            userPage = clothesService.getClothesBySizeNameAndBrandId(size, brandId, pageable);
            color = "";
            priceRange = "";
            keyword = "";
        }
        else if (!color.isEmpty()) {
            userPage = clothesService.getClothesByColorNameColorAndBrandId(color, brandId, pageable);
            size = "";
            priceRange = "";
            keyword = "";
        }
        else if (!priceRange.isEmpty()) {
            switch (priceRange) {
                case "under500k":
                    userPage = clothesService.getClothesByBrandIdAndPriceRange(brandId, 0, 500000, keyword, pageable);
                    break;
                case "under1m":
                    userPage = clothesService.getClothesByBrandIdAndPriceRange(brandId, 0, 1000000, keyword, pageable);
                    break;
                case "above1m":
                    userPage = clothesService.getClothesByBrandIdAndPriceRange(brandId, 1000000, Integer.MAX_VALUE, keyword, pageable);
                    break;
                default:
                    userPage = clothesService.getClothesByBrandIdAndKeyword(brandId, keyword, pageable);
            }
            size = "";
            color = "";
            keyword = "";
        }
        else {
            userPage = clothesService.getClothesByBrandIdAndKeyword(brandId, keyword, pageable);
        }

        model.addAttribute("aboutInfo", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("priceRange", priceRange);
        model.addAttribute("brandId", brandId);
        model.addAttribute("color", color);
        model.addAttribute("size", size);

        return "bottom";
    }



    @GetMapping("/bags")
    public String bags(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "") String priceRange,
            @RequestParam(defaultValue = "") String color,
            @RequestParam(defaultValue = "") String size
    ) {
        int brandId = 3;
        int pageSize = 4;
        Pageable pageable = sortOrder.equals("asc") ?
                PageRequest.of(page, pageSize, Sort.by("price").ascending()) :
                PageRequest.of(page, pageSize, Sort.by("price").descending());

        Page<Clothes> userPage;

        if (!size.isEmpty()) {
            userPage = clothesService.getClothesBySizeNameAndBrandId(size, brandId, pageable);
            color = "";
            priceRange = "";
            keyword = "";
        }
        else if (!color.isEmpty()) {
            userPage = clothesService.getClothesByColorNameColorAndBrandId(color, brandId, pageable);
            size = "";
            priceRange = "";
            keyword = "";
        }
        else if (!priceRange.isEmpty()) {
            switch (priceRange) {
                case "under500k":
                    userPage = clothesService.getClothesByBrandIdAndPriceRange(brandId, 0, 500000, keyword, pageable);
                    break;
                case "under1m":
                    userPage = clothesService.getClothesByBrandIdAndPriceRange(brandId, 0, 1000000, keyword, pageable);
                    break;
                case "above1m":
                    userPage = clothesService.getClothesByBrandIdAndPriceRange(brandId, 1000000, Integer.MAX_VALUE, keyword, pageable);
                    break;
                default:
                    userPage = clothesService.getClothesByBrandIdAndKeyword(brandId, keyword, pageable);
            }
            size = "";
            color = "";
            keyword = "";
        }
        else {
            userPage = clothesService.getClothesByBrandIdAndKeyword(brandId, keyword, pageable);
        }

        model.addAttribute("aboutInfo", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("priceRange", priceRange);
        model.addAttribute("brandId", brandId);
        model.addAttribute("color", color);
        model.addAttribute("size", size);

        return "bag";
    }



    @GetMapping("/outerwears")
    public String outerwears(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "") String priceRange,
            @RequestParam(defaultValue = "") String color,
            @RequestParam(defaultValue = "") String size
    ) {
        int brandId = 4;
        int pageSize = 4;
        Pageable pageable = sortOrder.equals("asc") ?
                PageRequest.of(page, pageSize, Sort.by("price").ascending()) :
                PageRequest.of(page, pageSize, Sort.by("price").descending());

        Page<Clothes> userPage;

        if (!size.isEmpty()) {
            userPage = clothesService.getClothesBySizeNameAndBrandId(size, brandId, pageable);
            color = "";
            priceRange = "";
            keyword = "";
        }
        else if (!color.isEmpty()) {
            userPage = clothesService.getClothesByColorNameColorAndBrandId(color, brandId, pageable);
            size = "";
            priceRange = "";
            keyword = "";
        }
        else if (!priceRange.isEmpty()) {
            switch (priceRange) {
                case "under500k":
                    userPage = clothesService.getClothesByBrandIdAndPriceRange(brandId, 0, 500000, keyword, pageable);
                    break;
                case "under1m":
                    userPage = clothesService.getClothesByBrandIdAndPriceRange(brandId, 0, 1000000, keyword, pageable);
                    break;
                case "above1m":
                    userPage = clothesService.getClothesByBrandIdAndPriceRange(brandId, 1000000, Integer.MAX_VALUE, keyword, pageable);
                    break;
                default:
                    userPage = clothesService.getClothesByBrandIdAndKeyword(brandId, keyword, pageable);
            }
            size = "";
            color = "";
            keyword = "";
        }
        else {
            userPage = clothesService.getClothesByBrandIdAndKeyword(brandId, keyword, pageable);
        }

        model.addAttribute("aboutInfo", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("priceRange", priceRange);
        model.addAttribute("brandId", brandId);
        model.addAttribute("color", color);
        model.addAttribute("size", size);

        return "outerwear";
    }




    @GetMapping("/detail/{id}")
    public String getProductDetail(@PathVariable("id") Integer id, Model model) {
        Clothes product = clothesService.getClothesById(id);

        if (product == null) {
            return "error";
        }
        model.addAttribute("product", product);
        model.addAttribute("aboutInfo", clothesService.getClothesByBrandId(product.getBrand().getId()));

        return "detail";
    }


}
