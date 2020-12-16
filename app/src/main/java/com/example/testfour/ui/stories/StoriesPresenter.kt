package com.example.testfour.ui.stories

import com.example.testfour.data.model.Story

interface StoriesPresenter {

    fun setView(storiesView: StoriesView)

    fun getData()

    fun deleteStory(story: Story)

    fun getStoryListById(listId: Int)

    fun updateStoryList(newStory: Story)
}