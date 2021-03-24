package com.project.covid19info

import com.project.covid19info.common.BASE_URL
import com.project.covid19info.common.BASE_URL1
import com.project.covid19info.common.HEADER
import com.project.covid19info.common.HEADER1
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilderUzb {
    companion object {
        operator fun invoke(): ApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder().addHeader("X-RapidAPI-Key", HEADER1)
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL1)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

        }
    }
}