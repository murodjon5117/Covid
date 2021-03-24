package com.project.covid19info.modelTotalUzb

import com.google.gson.annotations.SerializedName

data class TotalUzbBetwen(

	@field:SerializedName("statusMsg")
	val statusMsg: String? = null,

	@field:SerializedName("records")
	val records: List<RecordsItem?>? = null
)