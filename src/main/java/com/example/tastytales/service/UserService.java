package com.example.tastytales.service;

import com.example.tastytales.config.jwt.JwtProvider;
import com.example.tastytales.dto.UserDTO;
import com.example.tastytales.dto.request.UserRequestDTO;
import com.example.tastytales.dto.response.FoodResponseDTO;
import com.example.tastytales.entity.FoodEntity;
import com.example.tastytales.entity.UserEntity;
import com.example.tastytales.repository.FoodRepository;
import com.example.tastytales.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public void save(UserDTO userDTO){
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(userDTO.getUserId());
        if(optionalUserEntity.isEmpty()){
            UserEntity userEntity = UserEntity.builder()
                    .userId(userDTO.getUserId())
                    .password(userDTO.getPassword())
                    .nickname(userDTO.getNickname())
                    .build();

            userRepository.save(userEntity);
        }
        else {
            throw new IllegalArgumentException("중복된 아이디 입니다.");
        }
    }

    public String login(UserRequestDTO userRequestDTO){
        UserEntity userEntity = userRepository.findByUserIdAndPassword(userRequestDTO.getUserId(), userRequestDTO.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("Not Found UserID or userPassword"));


        return jwtProvider.generateToken(userEntity, Duration.ofHours(2));
    }
}
