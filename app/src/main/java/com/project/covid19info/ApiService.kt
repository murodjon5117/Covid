package com.project.covid19info

import com.project.covid19info.model.CovidResponse
import com.project.covid19info.modelTotal.Total
import com.project.covid19info.common.DATA
import com.project.covid19info.common.DATAUZB
import com.project.covid19info.common.TOTAL
import com.project.covid19info.common.TOTALUZ
import com.project.covid19info.modelTotalUzb.TotalUzbBetwen
import com.project.covid19info.modelUzb.ResUzb
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(DATA)
    suspend fun loadBetweenDate(@Query("start_date") date1: String
                                , @Query("end_date")date2: String )
    : Response<CovidResponse>

    @GET(DATAUZB)
    suspend fun loadBetweenDateCountery(@Query("start_date") date1: String
                                , @Query("end_date")date2: String , @Query("country_code")code: String)
            : Response<TotalUzbBetwen>


    @GET(TOTALUZ)
    suspend fun loadUzb(@Query("country") uzb: String)
            : Response<ResUzb>

    @GET(TOTAL)
    suspend fun loadTotal()
            : Response<Total>

}