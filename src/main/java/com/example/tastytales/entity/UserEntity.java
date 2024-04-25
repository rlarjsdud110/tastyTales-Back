package com.example.tastytales.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    int userNo;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "password")
    private String password;
    @Column(name = "nickname")
    private String nickname;

}
