package com.example.testfour.di

import com.example.testfour.ui.stories.StoryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun presenterModule() = module {
    viewModel { StoryViewModel(get()) }
}