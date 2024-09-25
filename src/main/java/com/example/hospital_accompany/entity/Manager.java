package com.example.hospital_accompany.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "managers")
@PrimaryKeyJoinColumn(name = "manager_id", referencedColumnName = "user_id")
public class Manager extends User {
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "profile_image", length = 255)
    private String profileImage;

    @Column(name = "career", length = 255)
    private String career;

    @Column(name = "comment", length = 255)
    private String comment;

    @Column(name = "working_region", length = 255)
    private String workingRegion;

    // 추가적인 속성 및 메서드
}