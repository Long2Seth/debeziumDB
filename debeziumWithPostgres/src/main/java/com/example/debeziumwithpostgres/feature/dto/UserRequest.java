package com.example.debeziumwithpostgres.feature.dto;

public record UserRequest(
        String name,
        String email,
        String phoneNumber,
        String address
) {
}
