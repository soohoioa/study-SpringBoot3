package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String price;
}
/**
 * 피자 등록 : POST -> /pizzas
 * 피자 단건 조회 : GET -> /pizzas/{id}
 * 피자 목록 조회 : GET -> /pizzas
 * 피자 수정 : PATCH -> /pizzas/{id}
 * 피자 삭제 : DELETE -> /pizzas/{id}
 */