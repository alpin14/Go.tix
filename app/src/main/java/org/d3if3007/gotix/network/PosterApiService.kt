package org.d3if3007.gotix.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://raw.githubusercontent.com/" + "alpin14/asesmentMob_json/main/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface PosterApiService {
    @GET("poster.json")
    suspend fun getPoster(): String
}

object PosterApi{
    val service: PosterApiService by lazy {
        retrofit.create(PosterApiService::class.java)
    }
}