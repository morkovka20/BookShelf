package com.example.bookshelf.model

data class Book(
    val id: Int,
    val title: String,
    val description: String,
    val author: String,
    val year: Int,
    val rating: Float,
    val isFavorite: Boolean = false
)