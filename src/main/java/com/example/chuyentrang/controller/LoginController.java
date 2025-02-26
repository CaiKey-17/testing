package com.example.chuyentrang.controller;

import com.example.chuyentrang.config.JwtTokenUtil;
import com.example.chuyentrang.service.CustomUserDetailsService;
import com.example.chuyentrang.service.EmailService;
import com.example.chuyentrang.service.RoleService;
import com.example.chuyentrang.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String login() {
        return "loginAdmin";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpServletResponse response) {
        boolean authenticated = userService.authenticateUser(username, password);
        if (authenticated) {
            try {
                String role = userService.getUserRole(username); // Lấy vai trò từ UserService
                String token = JwtTokenUtil.createToken(username, role); // Tạo token với vai trò
                model.addAttribute("token", token);

                // Add token to the cookie
                Cookie tokenCookie = new Cookie("token", token);
                tokenCookie.setHttpOnly(true);
                System.out.println("Token: " + token);
                System.out.println("Is token valid: " + JwtTokenUtil.verifyToken(token));
                System.out.println("Authentication: " + SecurityContextHolder.getContext().getAuthentication());

                tokenCookie.setPath("/");
                response.addCookie(tokenCookie);

                return "redirect:/admin";
            } catch (Exception e) {
                model.addAttribute("error", "Error creating token");
                return "loginAdmin";
            }
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "loginAdmin";
        }
    }



    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }


    @GetMapping("/forgot")
    public String forgot() {
        return "forgot";
    }

    @GetMapping("/change")
    public String change() {
        return "change";
    }

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Autowired
    private EmailService emailService;


    @PostMapping("/forgot")
    public String resetPassword(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        try {
            customUserDetailsService.resetPasswordToDefault(email);

            String message = "Mật khẩu mới là: 111\nVui lòng không chia sẻ cho ai";
            emailService.sendSimpleEmail(email, "Khôi phục mật khẩu", message);

            return "redirect:/login?resetSuccess=true";
        } catch (UsernameNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email không tồn tại trong hệ thống.");
            return "redirect:/forgot";
        }
    }




    @PostMapping("/change")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 Authentication authentication) {
        String email = authentication.getName();
        boolean success = customUserDetailsService.changePassword(oldPassword, newPassword, email);

        if (success) {
            return "redirect:/login?changeSuccess=true";
        } else {
            return "redirect:/change?error=true";
        }
    }





}
