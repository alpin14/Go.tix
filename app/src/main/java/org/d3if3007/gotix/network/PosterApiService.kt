package org.d3if3007.gotix.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3007.gotix.model.Movie
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://raw.githubusercontent.com/" + "alpin14/asesmentMob_json/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface PosterApiService {
    @GET("poster.json")
    suspend fun getPoster(): List<Movie>
}

object PosterApi{
    val service: PosterApiService by lazy {
        retrofit.create(PosterApiService::class.java)
    }

    fun getPosterUrl(imageId: String): String{
        return "$BASE_URL$imageId.jpg"
    }
}

enum class ApiStatus {LOADING, SUCCES, FAILED}