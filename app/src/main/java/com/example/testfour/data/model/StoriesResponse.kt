package com.example.testfour.data.model

import com.google.gson.annotations.SerializedName

class StoriesResponse(
    @SerializedName("hits") val story: List<Story>
)