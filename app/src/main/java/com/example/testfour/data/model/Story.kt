package com.example.testfour.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "stories")
data class Story(
    @PrimaryKey val id: Int,
    @SerializedName("story_url")
    val url: String?,
    @SerializedName("story_title")
    val titleOne: String?,
    @SerializedName("author")
    val name: String,
    @SerializedName("created_at")
    var date: String,
    @SerializedName("title")
    val titleOption: String?,
    val isDelete: Boolean = false
): Parcelable