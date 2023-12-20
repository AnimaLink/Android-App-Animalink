package com.riveong.animalink.data.model

import com.riveong.animalink.R

data class Animal(
    val id : Int,
    val image: String,
    val title: String,
    val species: String,
    val Status: String,
)

data class AnimalExtended(
    val id : Int,
    val image: String,
    val title: String,
    val species: String,
    val Status: String,
    val price: String,
    val description: String
)

