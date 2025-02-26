package com.example.chuyentrang.controller;

import com.example.chuyentrang.service.CustomUserDetailsService;
import com.example.chuyentrang.service.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc(addFilters = false)

public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private EmailService emailService;

    @MockBean
    private Authentication authentication;

    // Test GET "/login"
    @Test
    void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginAdmin"));
    }


    // Test GET "/logout"
    @Test
    void testLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    // Test GET "/forgot"
    @Test
    void testForgotPage() throws Exception {
        mockMvc.perform(get("/forgot"))
                .andExpect(status().isOk())
                .andExpect(view().name("forgot"));
    }

    // Test GET "/change"
    @Test
    void testChangePasswordPage() throws Exception {
        mockMvc.perform(get("/change"))
                .andExpect(status().isOk())
                .andExpect(view().name("change"));
    }

    // Test POST "/forgot" (success case)
    @Test
    void testResetPasswordSuccess() throws Exception {
        // Mocking successful password reset and email sending
        Mockito.doNothing().when(customUserDetailsService).resetPasswordToDefault(anyString());
        Mockito.doNothing().when(emailService).sendSimpleEmail(anyString(), anyString(), anyString());

        mockMvc.perform(post("/forgot").param("email", "test@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?resetSuccess=true"));
    }

    // Test POST "/forgot" (failure case)
    @Test
    void testResetPasswordFailure() throws Exception {
        // Mocking failure (UsernameNotFoundException)
        Mockito.doThrow(new UsernameNotFoundException("Email not found"))
                .when(customUserDetailsService).resetPasswordToDefault(anyString());

        mockMvc.perform(post("/forgot").param("email", "invalid@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/forgot"))
                .andExpect(flash().attributeExists("errorMessage"));
    }

    // Test POST "/change" (success case)
    @Test
    void testChangePasswordSuccess() throws Exception {
        // Mocking authentication and successful password change
        Mockito.when(authentication.getName()).thenReturn("test@example.com");
        Mockito.when(customUserDetailsService.changePassword(anyString(), anyString(), anyString()))
                .thenReturn(true);

        mockMvc.perform(post("/change")
                        .param("oldPassword", "oldPass123")
                        .param("newPassword", "newPass123")
                        .principal(authentication))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?changeSuccess=true"));
    }

    // Test POST "/change" (failure case)
    @Test
    void testChangePasswordFailure() throws Exception {
        // Mocking authentication and failure for password change
        Mockito.when(authentication.getName()).thenReturn("test@example.com");
        Mockito.when(customUserDetailsService.changePassword(anyString(), anyString(), anyString()))
                .thenReturn(false);

        mockMvc.perform(post("/change")
                        .param("oldPassword", "wrongOldPass")
                        .param("newPassword", "newPass123")
                        .principal(authentication))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/change?error=true"));
    }
}
