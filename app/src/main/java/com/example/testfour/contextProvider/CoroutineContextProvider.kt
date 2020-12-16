package com.example.testfour.contextProvider

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {

    fun context(): CoroutineContext
}