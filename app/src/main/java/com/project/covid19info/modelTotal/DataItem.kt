package com.project.covid19info.modelTotal

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("recovered")
	val recovered: String? = null,

	@field:SerializedName("active")
	val active: String? = null,

	@field:SerializedName("newdeaths")
	val newdeaths: String? = null,

	@field:SerializedName("newconfirmed")
	val newconfirmed: String? = null,

	@field:SerializedName("confirmed")
	val confirmed: String? = null,

	@field:SerializedName("deaths")
	val deaths: String? = null
)