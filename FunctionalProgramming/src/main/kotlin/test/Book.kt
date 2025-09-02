package test

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val title: String,
    val author: String,
    val year: Int
)
