package com.example.tastytales.config.jwt;

import com.example.tastytales.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Service
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(UserEntity userEntity, Duration duration){
        Date nowDate = new Date();
        return makeToken(new Date(nowDate.getTime()+duration.toMillis()),userEntity);
    }

    private String makeToken(Date date, UserEntity userEntity){
        Date nowDate = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(nowDate)
                .setExpiration(date)
                .setSubject(userEntity.getUserId())
                .claim("id", userEntity.getUserNo())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public boolean validToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(
                new User(claims.getSubject(), "", authorities), token, authorities);
    }
    private Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
    public Integer getUserNo(String token){
        Claims claims = getClaims(token);
        return claims.get("id", Integer.class);
    }
}
