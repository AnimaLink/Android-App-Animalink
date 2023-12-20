package com.riveong.animalink.data.model

data class UserResponse(
	val data: Data,
	val message: String,
	val status: String
)

data class UserData(
	val createdAt: String,
	val wa_number: String,
	val last_Name: String,
	val id: Int,
	val avatar: String,
	val first_name: String,
	val email: String,
	val updatedAt: String
)

data class Data(
	val userData: UserData
)

