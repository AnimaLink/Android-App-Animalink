package com.riveong.animalink.data.model

import com.google.gson.annotations.SerializedName

data class GetCommentNew(

	@field:SerializedName("data")
	val data: NewData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class User(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("avatar")
	val avatar: String
)

data class NewData(

	@field:SerializedName("listComment")
	val listComment: List<NewListCommentItem>
)

data class NewListCommentItem(

	@field:SerializedName("comment")
	val comment: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("user")
	val user: User? = null
)
