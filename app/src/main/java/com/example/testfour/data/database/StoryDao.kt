package com.example.testfour.data.database

import androidx.room.*
import com.example.testfour.data.model.Story

@Dao
interface StoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveStories(stories: List<Story>)

    @Query("SELECT * FROM stories WHERE isDelete = 0")
    suspend fun getSavedStories(): List<Story>

    @Delete
    suspend fun deleteStory(story: Story)

    @Query("SELECT * FROM stories WHERE id = :listId ")
    suspend fun getStoryListById(listId: Int): Story

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStoryList(newStoryList: Story)
}