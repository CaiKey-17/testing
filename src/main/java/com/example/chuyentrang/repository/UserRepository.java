package com.example.chuyentrang.repository;

import com.example.chuyentrang.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Modifying
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.username = :username")
    int updatePasswordByEmail(@Param("username") String username, @Param("newPassword") String newPassword);

    User findByUsername(String username);

}

