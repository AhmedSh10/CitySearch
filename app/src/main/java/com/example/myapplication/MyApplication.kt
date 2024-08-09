package com.example.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

@HiltAndroidApp
class MyApplication : Application()