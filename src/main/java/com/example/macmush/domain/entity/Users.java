package com.example.macmush.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA를 위한 기본 생성자
@Table(name = "Users") // 실제 DB 테이블 이름 지정
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 50, unique = true, nullable = false)
    private String nickname;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @CreationTimestamp // INSERT 시 자동으로 현재 시간 저장
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // UPDATE 시 자동으로 현재 시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}