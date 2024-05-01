package com.example.tastytales.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
    private String title;
    private String address;
    private String image;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updated_at;
    private String status;
}
