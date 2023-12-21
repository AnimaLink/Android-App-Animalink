package com.riveong.animalink.data.model

import com.google.gson.annotations.SerializedName

data class DetectResponse(

	@field:SerializedName("model_confidence")
	val modelConfidence: String? = null,

	@field:SerializedName("animal_status")
	val animalStatus: String? = null,

	@field:SerializedName("predicted_animal")
	val predictedAnimal: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
