package com.riveong.animalink.data.model

import com.riveong.animalink.R

data class Animal(
    val image: Int,
    val title: String,
    val species: String,
    val Owner: String,
)

val animalsDummy = listOf(
    Animal(R.drawable.necoarc, "Fluffy", "🐈 Cat", "Alice"),
    Animal(R.drawable.necoarc, "Buddy", "🐕 Dog", "Bob"),
    Animal(R.drawable.necoarc, "Cotton", "🐇 Rabbit", "Charlie"),
    Animal(R.drawable.necoarc, "Polly2", "🦜 Parrot", "David"),
    Animal(R.drawable.necoarc, "Polly3", "🦜 Parrot", "David"),
    Animal(R.drawable.necoarc, "Polly4", "🦜 Parrot", "David"),
    Animal(R.drawable.necoarc, "Polly5", "🦜 Parrot", "David"),
    // Add more animals as needed
)