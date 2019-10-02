package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.myapplication.Post as Post

interface MyAPI {
    @GET("photos_public.gne")
    fun listRepos(
        @Query("tags") tags: String,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1
    ): Call<Post>
}