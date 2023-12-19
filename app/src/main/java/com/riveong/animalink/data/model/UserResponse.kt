package com.riveong.animalink.data.model

data class UserResponse(
	val data: Data,
	val message: String,
	val status: String
)

data class UserData(
	val createdAt: String,
	val waNumber: String,
	val lastName: String,
	val id: Int,
	val avatar: String,
	val firstName: String,
	val email: String,
	val updatedAt: String
)

data class Data(
	val userData: UserData
)

