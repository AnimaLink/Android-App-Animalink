package com.riveong.animalink.data.model

import com.google.gson.annotations.SerializedName

data class SingleForumResponse(

	@field:SerializedName("data")
	val data: SingleData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class SingleStatus(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Forum(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: Type? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("status")
	val status: Status? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class SingleCategory(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class SingleType(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class SingleData(

	@field:SerializedName("forum")
	val forum: Forum? = null
)
