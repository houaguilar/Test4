package com.example.testfour.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testfour.data.model.Story

@Database(entities = [Story::class], version = 1)
abstract class StoryDatabase: RoomDatabase(){

    abstract fun storyDao(): StoryDao

    companion object {

        fun create(context: Context): StoryDatabase {

            return Room.databaseBuilder(
                context,
                StoryDatabase::class.java,
                "stories"
            ).build()
        }
    }
}