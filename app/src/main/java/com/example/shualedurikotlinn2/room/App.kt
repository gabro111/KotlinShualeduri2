package com.example.shualedurikotlinn2.room

import android.app.Application
import androidx.room.Room
import com.example.shualedurikotlinn2.api.RetrofitClient

class App:Application() {

    lateinit var db:AppDatabase
    companion object{
        lateinit var instance:App
        private set
    }

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.initClient()
        instance = this
        db= Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "APP_DATABASE"
        ).allowMainThreadQueries().build()
    }
}