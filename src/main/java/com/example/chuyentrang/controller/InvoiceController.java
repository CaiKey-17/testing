package com.example.chuyentrang.controller;

import com.example.chuyentrang.model.Order;
import com.example.chuyentrang.model.Product;
import com.example.chuyentrang.repository.OrderRepository;
import com.example.chuyentrang.service.EmailService;
import com.example.chuyentrang.service.OrderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private OrderService orderService;

    public InvoiceController(EmailService emailService, OrderService orderService) {
        this.emailService = emailService;
        this.orderService = orderService;
    }
    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Map<String, Object> invoiceData) {
        Order order = new Order();
        order.setName((String) invoiceData.get("name"));
        order.setEmail((String) invoiceData.get("email"));
        order.setPhone((String) invoiceData.get("phone"));
        order.setAddress((String) invoiceData.get("address"));
        order.setTotal((String) invoiceData.get("total"));
        order.setProcess(0);
        List<Map<String, Object>> productsData = (List<Map<String, Object>>) invoiceData.get("products");
        List<Product> products = new ArrayList<>();

        if (productsData != null) {
            for (Map<String, Object> productData : productsData) {
                Product product = new Product();
                product.setName((String) productData.get("name"));
                product.setPrice((String) productData.get("price"));
                product.setQuantity((Integer) productData.get("quantity"));
                product.setColor((String) productData.get("color"));
                product.setSize((String) productData.get("size"));
                products.add(product);
            }
            order.setProducts(products);
        }

        Order savedOrder = orderService.addOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }



    @PostMapping
    public String sendInvoice(@RequestBody Map<String, Object> invoiceData) {
        String name = (String) invoiceData.get("name");
        String email = (String) invoiceData.get("email");
        String phone = (String) invoiceData.get("phone");
        String address = (String) invoiceData.get("address");
        String total = (String) invoiceData.get("total");

        List<Map<String, Object>> products = (List<Map<String, Object>>) invoiceData.get("products");

        StringBuilder body = new StringBuilder();
        body.append("<h1>Hóa đơn của bạn</h1>")
                .append("<h2>Thông tin khách hàng:</h2>")
                .append("<p>Tên: ").append(name).append("</p>")
                .append("<p>Email: ").append(email).append("</p>")
                .append("<p>Số điện thoại: ").append(phone).append("</p>")
                .append("<p>Địa chỉ: ").append(address).append("</p>")
                .append("<p>Tổng hoá đơn: ").append(total).append("</p>")
                .append("<p>Tình trạng đơn hàng: Đang chờ phê duyệt </p>")
                .append("<h2>Chi tiết hóa đơn:</h2>")
                .append("<table style='width: 100%; border-collapse: collapse;'>")
                .append("<tr style='background-color: #f2f2f2;'>")
                .append("<th style='border: 1px solid #ddd; padding: 8px;'>Sản phẩm</th>")
                .append("<th style='border: 1px solid #ddd; padding: 8px;'>Giá</th>")
                .append("<th style='border: 1px solid #ddd; padding: 8px;'>Số lượng</th>")
                .append("<th style='border: 1px solid #ddd; padding: 8px;'>Màu sắc</th>")
                .append("<th style='border: 1px solid #ddd; padding: 8px;'>Kích thước</th>")
                .append("</tr>");

        for (Map<String, Object> product : products) {
            String productName = (String) product.get("name");
            String price = (String) product.get("price");
            int quantity = (int) product.get("quantity");
            String color = (String) product.get("color");
            String size = (String) product.get("size");

            body.append("<tr>")
                    .append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(productName).append("</td>")
                    .append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(price).append("</td>")
                    .append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(quantity).append("</td>")
                    .append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(color).append("</td>")
                    .append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(size).append("</td>")
                    .append("</tr>");
        }

        body.append("</table>")
                .append("<p>Cảm ơn bạn đã mua hàng!</p>");

        emailService.sendInvoiceEmail(email, "Xác nhận hoá đơn từ NCK SHOP", body.toString());
        StringBuilder body1 = new StringBuilder();
        body1.append("<h1>Thông báo đơn hàng mới</h1>")
                .append("<p>Chào bạn!</p>")
                .append("<p>Bạn có một đơn hàng mới cần duyệt.</p>")
                .append("<p>Thông tin khách hàng:</p>")
                .append("<p>Tên: ").append(name).append("</p>")
                .append("<p>Email: ").append(email).append("</p>")
                .append("<p>Số điện thoại: ").append(phone).append("</p>")
                .append("<p>Địa chỉ: ").append(address).append("</p>")
                .append("<p>Tổng hoá đơn: ").append(total).append("</p>")
                .append("<p>Vui lòng duyệt đơn hàng tại: <a href='http://localhost:8080/donhang'>Xem đơn hàng</a></p>")
                .append("<p>Cảm ơn!</p>");

        // Gửi email cho quản trị viên
        emailService.sendInvoiceEmail("caoky.sonha@gmail.com", "Xác nhận đơn hàng mới", body1.toString());
        return "Hóa đơn đã được gửi qua email!";
    }

}
