package com.example.testfour.ui.stories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testfour.data.model.Story
import com.example.testfour.domain.StoryRepository
import com.example.testfour.utils.logCoroutine
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StoryViewModel(private val storyRepository: StoryRepository): ViewModel(), StoriesPresenter {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        throwable.printStackTrace()
    }

    private lateinit var storiesView: StoriesView

    override fun setView(storiesView: StoriesView) {
        this.storiesView = storiesView
    }

    override fun getData() {
        viewModelScope.launch(coroutineExceptionHandler){
            logCoroutine("getData", coroutineContext)

            delay(500)
            val result = runCatching { storyRepository.getStories() }

            Log.d("TestCoroutine", "Still Alive!")
            result.onSuccess { stories ->
                storiesView.showStories(stories)
            }.onFailure { error ->
                handleError(error)
            }
        }
    }

    override fun deleteStory(story: Story) {
        viewModelScope.launch {
            storyRepository.deleteStories(story)
        }
    }

    override fun getStoryListById(listId: Int) {
        viewModelScope.launch {
            storyRepository.getStoryListById(listId)
        }
    }

    override fun updateStoryList(newStory: Story) {
        viewModelScope.launch {
            storyRepository.updateStoryList(newStory)
        }
    }


    private fun handleError(throwable: Throwable) {
        storiesView.showError(throwable)
    }

}