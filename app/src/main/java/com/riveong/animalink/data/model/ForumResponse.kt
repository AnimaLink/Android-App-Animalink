package com.riveong.animalink.data.model

import com.google.gson.annotations.SerializedName

data class ForumResponse(

	@field:SerializedName("data")
	val data: ForumData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Status(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class ListForumItem(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: Type? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("status")
	val status: Status? = null
)

data class ForumData(

	@field:SerializedName("listForum")
	val listForum: List<ListForumItem?>? = null
)

data class Type(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Category(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
