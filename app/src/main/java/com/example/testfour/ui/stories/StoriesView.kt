package com.example.testfour.ui.stories

import com.example.testfour.data.model.Story

interface StoriesView {

    fun showStories(stories: List<Story>)

//    fun onStorySwipe(story: Story, position: Int)

    fun showError(throwable: Throwable)
}