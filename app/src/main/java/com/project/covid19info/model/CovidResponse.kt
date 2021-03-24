package com.project.covid19info.model

import com.google.gson.annotations.SerializedName

data class CovidResponse(

	@field:SerializedName("statusMsg")
	val statusMsg: String? = null,

	@field:SerializedName("records")
	val records: List<RecordsItem?>? = null
)