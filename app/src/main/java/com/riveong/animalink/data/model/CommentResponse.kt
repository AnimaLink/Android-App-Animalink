package com.riveong.animalink.data.model

import com.google.gson.annotations.SerializedName

data class CommentResponse(

	@field:SerializedName("data")
	val data: CommentData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ListCommentItem(

	@field:SerializedName("comment")
	val comment: String,

	@field:SerializedName("userId")
	val userId: Int? = null
)

data class CommentData(

	@field:SerializedName("listComment")
	val listComment: List<ListCommentItem?>? = null
)
