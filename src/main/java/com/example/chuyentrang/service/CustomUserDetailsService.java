package com.example.chuyentrang.service;
import com.example.chuyentrang.model.User;
import com.example.chuyentrang.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void resetPasswordToDefault(String email) {
        String encodedPassword = passwordEncoder.encode("111");
        int updatedRows = userRepository.updatePasswordByEmail(email, encodedPassword);
        if (updatedRows == 0) {
            throw new UsernameNotFoundException("Email không tồn tại trong hệ thống.");
        }
    }

    @Transactional
    public boolean changePassword(String oldPassword, String newPassword, String email) {
        User user = (User) loadUserByUsername(email);

        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }



}

