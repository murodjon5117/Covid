package com.project.covid19info.model

import com.google.gson.annotations.SerializedName

data class RecordsItem(

	@field:SerializedName("dateofrecord")
	val dateofrecord: String? = null,

	@field:SerializedName("cases")
	val cases: Cases? = null
)