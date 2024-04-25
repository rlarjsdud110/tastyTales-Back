package com.example.tastytales.dto.response;

import com.example.tastytales.entity.FoodEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FoodResponseDTO {
    private String title;
    private String address;
    private String category;
    private String image;
    private String url;
    public static FoodResponseDTO toEntity(FoodEntity foodEntity){
        return FoodResponseDTO.builder()
                .title(foodEntity.getTitle())
                .address(foodEntity.getAddress())
                .category(foodEntity.getCategory())
                .image(foodEntity.getImage())
                .url(foodEntity.getUrl())
                .build();
    }
}
