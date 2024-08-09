package com.example.myapplication.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



data class City(
    val country: String,
    val name: String,
    val id: Long,
    val coord: Coord
)

data class Coord(
    val lon: Double,
    val lat: Double
)
