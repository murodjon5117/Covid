package com.project.covid19info

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }
    @SuppressLint("StaticFieldLeak")
    companion object{
        var context : Context? = null
    }

}