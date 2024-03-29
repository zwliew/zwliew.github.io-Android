package io.github.zwliew.zwliew.util

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val API_BASE_URL = "https://zwliew.netlify.com/"

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}