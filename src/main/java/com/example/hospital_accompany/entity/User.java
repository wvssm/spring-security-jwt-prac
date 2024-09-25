package com.example.hospital_accompany.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "role")
    private String role;

    // 공통 속성 및 메서드

    // 역할(Role)을 위한 Enum
    public enum Role {
        ROLE_USER, ROLE_MANAGER
    }
}