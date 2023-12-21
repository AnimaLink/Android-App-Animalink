package com.riveong.animalink.data.model

import com.google.gson.annotations.SerializedName

data class BehaviourResponse(

	@field:SerializedName("data")
	val data: BehaviourData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ListBehaviourAnimalItem(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("behaviour")
	val behaviour: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class BehaviourData(

	@field:SerializedName("listAnimal")
	val listAnimal: List<ListBehaviourAnimalItem?>? = null
)
