package com.example.moneytracker.service.model

data class JwtResponse(
    val success: Boolean,
    val jwt: String,
    val response: String
)