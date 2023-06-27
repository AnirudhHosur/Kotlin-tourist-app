package com.example.lab3

/**
 * Purpose: Represents the City class data model
 * Params: city name, image, language spoken, population count, and continent it belongs to
 */
data class City(
    val name: String,
    val imageResId: Int,
    val continent: String,
    val language: String,
    val population: String
)
