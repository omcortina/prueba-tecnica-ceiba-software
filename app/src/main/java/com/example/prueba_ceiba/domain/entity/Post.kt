package com.example.prueba_ceiba.domain.entity

data class Post(
    val id: Int,
    val user_id: Int,
    val title: String,
    val body: String
)