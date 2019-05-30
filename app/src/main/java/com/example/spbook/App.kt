package com.example.spbook

import android.app.Application
import com.example.spbook.DI.DaggerMainComponent
import com.example.spbook.DI.MainComponent
import com.example.spbook.DI.module.DataModule
import com.example.spbook.DI.module.MapModule

class App: Application() {
    companion object {
        lateinit var daggerMainComponent: MainComponent

    }
    override fun onCreate() {
        super.onCreate()

        daggerMainComponent = DaggerMainComponent.builder()
            .dataModule(object : DataModule(applicationContext){})
            .mapModule(object :MapModule(applicationContext){})
            .build()
    }
}