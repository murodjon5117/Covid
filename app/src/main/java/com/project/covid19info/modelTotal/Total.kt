package com.project.covid19info.modelTotal

import com.google.gson.annotations.SerializedName

data class Total(

	@field:SerializedName("statusMsg")
	val statusMsg: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)