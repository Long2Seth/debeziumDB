package com.example.debeziumwithmysql.feature.dto;


import lombok.Builder;

@Builder
public record UserRequest(
        String name,
        String email,
        String phoneNumber,
        String address
) {
}
