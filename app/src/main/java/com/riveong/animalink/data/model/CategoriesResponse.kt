package com.riveong.animalink.data.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ListForumCategoryItem(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class CategoriesData(

	@field:SerializedName("listForumCategory")
	val listForumCategory: List<ListForumCategoryItem?>? = null
)
