package com.riveong.animalink.data.model

import com.google.gson.annotations.SerializedName

data class ForumAddResponse(

	@field:SerializedName("data")
	val data: AddData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class AddData(

	@field:SerializedName("forumId")
	val forumId: Int? = null
)
