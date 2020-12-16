package com.example.testfour.domain

import com.example.testfour.data.api.StoryApiService
import com.example.testfour.data.database.StoryDao
import com.example.testfour.data.model.Story

class StoryRepositoryImpl(private val storyApiService: StoryApiService, private val storyDao: StoryDao): StoryRepository{

    override suspend fun getStories(): List<Story> {
        val cachedStories = storyDao.getSavedStories()

        val apiStories = try {
            storyApiService.getStories("android")?.story
        } catch (error: Throwable){
            null
        }

        if (apiStories != null) {
            storyDao.saveStories(apiStories)
        }

        return apiStories ?: cachedStories
    }

    override suspend fun deleteStories(story: Story) {
        return storyDao.deleteStory(story)
    }

    override suspend fun getStoryListById(listId: Int): Story {
        return  storyDao.getStoryListById(listId)
    }

    override suspend fun updateStoryList(newStoryList: Story) {
        storyDao.updateStoryList(newStoryList)
    }

}