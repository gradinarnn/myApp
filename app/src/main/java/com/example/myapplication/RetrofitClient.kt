package com.example.myapplication


import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var ourInstance: Retrofit? = null
    val instance: Retrofit
        get() {
            if (ourInstance == null){
                val gson = GsonBuilder()
                    .excludeFieldsWithModifiers()
                    .serializeNulls()
                    .enableComplexMapKeySerialization()
                    .create()
                var client = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
                ourInstance = Retrofit.Builder()
                    .baseUrl("https://www.flickr.com/services/feeds/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return ourInstance!!
        }
}