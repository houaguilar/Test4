package com.example.testfour.domain

import com.example.testfour.data.model.Story

interface StoryRepository {

    suspend fun getStories(): List<Story>

    suspend fun deleteStories(story: Story)

    suspend fun getStoryListById(listId: Int): Story

    suspend fun updateStoryList(newStoryList: Story)
}