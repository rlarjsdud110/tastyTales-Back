package com.example.tastytales.dto.request;

import com.example.tastytales.entity.FoodEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FoodRequestDTO {
    private String title;
    private String address;
    private String category;
    private String image;
    private String url;
    public static FoodRequestDTO toEntity(FoodEntity foodEntity){
        return FoodRequestDTO.builder()
                .title(foodEntity.getTitle())
                .address(foodEntity.getAddress())
                .category(foodEntity.getCategory())
                .image(foodEntity.getImage())
                .url(foodEntity.getUrl())
                .build();
    }
}
