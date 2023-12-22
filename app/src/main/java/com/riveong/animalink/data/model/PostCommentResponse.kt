package com.riveong.animalink.data.model

import com.google.gson.annotations.SerializedName

data class PostCommentResponse(

	@field:SerializedName("data")
	val data: AfterData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class AfterData(

	@field:SerializedName("commentId")
	val commentId: Int? = null
)
