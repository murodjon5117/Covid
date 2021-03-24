package com.project.covid19info.modelUzb

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("recovered")
	val recovered: Int? = null,

	@field:SerializedName("lastReported")
	val lastReported: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("confirmed")
	val confirmed: Int? = null,

	@field:SerializedName("lastChecked")
	val lastChecked: String? = null,

	@field:SerializedName("deaths")
	val deaths: Int? = null
)