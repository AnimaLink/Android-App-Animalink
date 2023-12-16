package com.riveong.animalink.ui.model

import com.riveong.animalink.R

data class Product(
    val image: Int,
    val title: String,
    val type: String,
    val Seller: String,
)

val productDummy = listOf(
    Product(R.drawable.corndog, "Premium Cat Food", "Food", "PetMart"),
    Product(R.drawable.corndog, "Large Dog Cage", "Cage", "PetSupplies"),
    Product(R.drawable.corndog, "Interactive Dog Toy", "Toy", "HappyPets"),
    Product(R.drawable.corndog, "Gentle Pet Shampoo", "Grooming", "CleanPets"),
    // Add more products as needed
)