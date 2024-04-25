package com.example.tastytales.repository;

import com.example.tastytales.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {
    Optional<List<FoodEntity>> findByStatus(String status);
    List<FoodEntity> findByCategory(String category);

    List<FoodEntity> findByUserNo(Integer userNo);
}
