package com.project.covid19info

class Repo {
    private var api:ApiService=RetrofitBuilder.invoke()
    suspend fun loadTotal()=api.loadTotal()
    suspend fun loadBetweenDate(date1:String,date2:String)=api.loadBetweenDate(date1,date2)
    suspend fun loadBetweenDateUzb(date1:String,date2:String,code:String)=api.loadBetweenDateCountery(date1,date2,code)
}