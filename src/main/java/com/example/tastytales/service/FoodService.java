package com.example.tastytales.service;

import com.example.tastytales.dto.request.FoodRequestDTO;
import com.example.tastytales.dto.response.FoodResponseDTO;
import com.example.tastytales.entity.FoodEntity;
import com.example.tastytales.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public List<FoodResponseDTO> findAll(){
        List<FoodEntity> entityList = foodRepository.findByStatus("A").get();
        List<FoodResponseDTO> dtoList = new ArrayList<>();
        for(FoodEntity foodEntity : entityList){
            dtoList.add(FoodResponseDTO.toEntity(foodEntity));
        }

        return dtoList;
    }

    public List<FoodResponseDTO> findCategory(String category){
        List<FoodEntity> entityList = foodRepository.findByCategory(category);
        List<FoodResponseDTO> dtoList = new ArrayList<>();

        if(entityList.isEmpty()){
            throw new IllegalArgumentException("not found "+ category);
        }

        for(FoodEntity foodEntity : entityList){
            dtoList.add(FoodResponseDTO.toEntity(foodEntity));
        }
        return dtoList;
    }

    public void saveFood(FoodRequestDTO foodRequestDTO){
        FoodEntity foodEntity = FoodEntity.builder()
                .title(foodRequestDTO.getTitle())
                .url(foodRequestDTO.getUrl())
                .image(foodRequestDTO.getImage())
                .address(foodRequestDTO.getAddress())
                .category(foodRequestDTO.getCategory())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status("A")
                .build();

        foodRepository.save(foodEntity);
    }


}
