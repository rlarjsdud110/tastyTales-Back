package com.example.tastytales.controller;

import com.example.tastytales.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<String> newAccessToken(@RequestHeader("R-AUTH-TOKEN") String refreshToken){
        log.info("[newAccessToken] 실행");
        String accessToken = tokenService.createAccessToken(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).body(accessToken);
    }

    @PostMapping("/jwt")
    public ResponseEntity<String> validationToken(@RequestHeader("X-AUTH-TOKEN") String accessToken){
        log.info("[validationToken] 실행");
        String userNickname = tokenService.validationToken(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(userNickname);
    }
}
