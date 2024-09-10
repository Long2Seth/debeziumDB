package com.example.debeziumwithpostgres.feature.dto;


import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String address
) {
}
