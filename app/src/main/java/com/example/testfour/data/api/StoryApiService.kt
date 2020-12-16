package com.example.testfour.data.api

import com.example.testfour.data.model.StoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StoryApiService {

    @GET("search_by_date")
    suspend fun getStories(@Query("query") query: String): StoriesResponse?
}