package com.example.marvelapplication.model

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object APIClient {

    private val BASE_URL = "https://www.breakingbadapi.com/api/"

    private val moshi by lazy { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }

    private val retroFit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    val apiService: ApiService by lazy {
        retroFit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("characters")
    fun fetchCharacters(@Query("limit") limit:Int = 10): Call<List<Character>>
}