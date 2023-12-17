package com.riveong.animalink.data.model

import com.riveong.animalink.R

data class Product(
    val image: Int,
    val title: String,
    val type: String,
    val Seller: String,
)

val productDummy = listOf(
    Product(R.drawable.corndog, "Premium Cat Food", "Food", "PetMart"),
    Product(R.drawable.corndog, "Larg2e Dog Cage", "Cage", "PetSupplies"),
    Product(R.drawable.corndog, "Int2eractive Dog Toy", "Toy", "HappyPets"),
    Product(R.drawable.corndog, "Gen3tle Pet Shampoo", "Grooming", "CleanPets"),
    Product(R.drawable.corndog, "Pr4emium Cat Food", "Food", "PetMart"),
    Product(R.drawable.corndog, "Larg3e Dog Cage", "Cage", "PetSupplies"),
    Product(R.drawable.corndog, "Inrteractive Dog Toy", "Toy", "HappyPets"),
    Product(R.drawable.corndog, "Gentlee Pet Shampoo", "Grooming", "CleanPets"),
    Product(R.drawable.corndog, "Prefmium Cat Food", "Food", "PetMart"),
    Product(R.drawable.corndog, "Largfe Dog Cage", "Cage", "PetSupplies"),
    Product(R.drawable.corndog, "Inter3active Dog Toy", "Toy", "HappyPets"),
    Product(R.drawable.corndog, "Gentl1e Pet Shampoo", "Grooming", "CleanPets"),
    // Add more products as needed
)