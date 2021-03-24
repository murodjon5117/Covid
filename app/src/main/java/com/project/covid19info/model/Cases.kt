package com.project.covid19info.model

import com.google.gson.annotations.SerializedName

data class Cases(

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("dailyrecovered")
	val dailyrecovered: String? = null,

	@field:SerializedName("country_name")
	val countryName: String? = null,

	@field:SerializedName("totalconfirmed")
	val totalconfirmed: String? = null,

	@field:SerializedName("totaldeceased")
	val totaldeceased: String? = null,

	@field:SerializedName("dailydeceased")
	val dailydeceased: String? = null,

	@field:SerializedName("totalrecovered")
	val totalrecovered: String? = null,

	@field:SerializedName("dailyconfirmed")
	val dailyconfirmed: String? = null
)