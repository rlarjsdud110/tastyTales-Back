package com.example.tastytales.controller;

import com.example.tastytales.dto.request.FoodRequestDTO;
import com.example.tastytales.dto.response.FoodResponseDTO;
import com.example.tastytales.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<List<FoodResponseDTO>> findAll(){
        List<FoodResponseDTO> resultList = foodService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }
    @GetMapping("/category")
    public ResponseEntity<List<FoodResponseDTO>> findCategory(@RequestParam("category") String category){
        List<FoodResponseDTO> resultList = foodService.findCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }

    @PostMapping
    public ResponseEntity<Void> saveFood(@RequestBody FoodRequestDTO foodRequestDTO){
        System.out.println(foodRequestDTO.getCategory());
        System.out.println(foodRequestDTO.getTitle());
        foodService.saveFood(foodRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
