package com.example.testfour.di

import com.example.testfour.contextProvider.CoroutineContextProvider
import com.example.testfour.contextProvider.CoroutineContextProviderImpl
import com.example.testfour.data.database.StoryDatabase
import com.example.testfour.domain.StoryRepository
import com.example.testfour.domain.StoryRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun appModule() = module {

    single { CoroutineContextProviderImpl(Dispatchers.IO) as CoroutineContextProvider}

    single { StoryDatabase.create(androidContext()) }

    single { get<StoryDatabase>().storyDao() }

    single { StoryRepositoryImpl(get(), get()) as StoryRepository }
}