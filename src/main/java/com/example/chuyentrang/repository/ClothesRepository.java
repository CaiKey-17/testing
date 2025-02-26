package com.example.chuyentrang.repository;

import com.example.chuyentrang.model.Clothes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClothesRepository extends JpaRepository<Clothes,Integer> {
    List<Clothes> findByBrandId(int brandId);
    Page<Clothes> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Clothes> findByBrandId(int brandId, Pageable pageable);
    Page<Clothes> findByPriceBetween(int minId, int maxId, Pageable pageable);


    Page<Clothes> findByBrandIdAndNameContainingIgnoreCase(int brandId, String name, Pageable pageable);

    Page<Clothes> findByBrandIdAndPriceBetween(int brandId, int minPrice, int maxPrice, Pageable pageable);

    Page<Clothes> findByBrandIdAndPriceBetweenAndNameContainingIgnoreCase(int brandId, int minPrice, int maxPrice, String name, Pageable pageable);

    @Query("SELECT DISTINCT c FROM Clothes c JOIN c.colors color WHERE color.nameColor = :nameColor")
    Page<Clothes> findByColorNameColor(@Param("nameColor") String nameColor, Pageable pageable);

    @Query("SELECT DISTINCT c FROM Clothes c JOIN c.sizes size WHERE size.name = :sizeName")
    Page<Clothes> findBySizeName(@Param("sizeName") String sizeName, Pageable pageable);

    @Query("SELECT DISTINCT c FROM Clothes c JOIN c.sizes size WHERE size.name = :sizeName AND c.brand.id = :brandId")
    Page<Clothes> findBySizeNameAndBrandId(@Param("sizeName") String sizeName, @Param("brandId") int brandId, Pageable pageable);

    @Query("SELECT DISTINCT c FROM Clothes c JOIN c.colors color WHERE color.nameColor = :colorName AND c.brand.id = :brandId")
    Page<Clothes> findByColorNameColorAndBrandId(@Param("colorName") String colorName, @Param("brandId") int brandId, Pageable pageable);


    @Query(value = "SELECT c FROM Clothes c ORDER BY c.createdDate DESC LIMIT 8")
    List<Clothes> findTop8ByOrderByCreatedDateDesc();
}
