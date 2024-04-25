package com.example.tastytales.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "food_map")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private int restaurantId;
    @Column(name = "title")
    private String title;
    @Column(name = "address")
    private String address;
    @Column(name = "image")
    private String image;
    @Column(name = "category")
    private String category;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "status")
    private String status;
    @Column(name = "url")
    private String url;
    @Column(name = "user_no")
    private int userNo;
}
