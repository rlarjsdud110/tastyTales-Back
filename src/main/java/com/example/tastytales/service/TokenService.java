package com.example.tastytales.service;

import com.example.tastytales.config.jwt.JwtProvider;
import com.example.tastytales.entity.TokenEntity;
import com.example.tastytales.entity.UserEntity;
import com.example.tastytales.repository.TokenRepository;
import com.example.tastytales.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
@Slf4j
public class TokenService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;


    public String createAccessToken(String refreshToken){
        if(!jwtProvider.validToken(refreshToken)){
            throw new IllegalArgumentException("Unexpected Token");
        }
        log.info("[createAccessToken] 실행");
        TokenEntity token = findByRefreshToken(refreshToken);

        UserEntity userEntity = userRepository.findByUserNo(jwtProvider.getUserNo(token.getRefreshToken())).get();

        return jwtProvider.generateToken(userEntity, Duration.ofHours(2));
    }

    public String validationToken(String accessToken){
        if(!jwtProvider.validToken(accessToken)){
            throw new IllegalArgumentException("Unexpected Token");
        }

        UserEntity userEntity = userRepository.findByUserNo(jwtProvider.getUserNo(accessToken)).get();

        return userEntity.getNickname();
    }

    private TokenEntity findByRefreshToken(String refreshToken){
        return tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Token"));
    }
}
