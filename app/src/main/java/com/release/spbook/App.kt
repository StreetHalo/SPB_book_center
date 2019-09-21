package com.release.spbook

import android.app.Application
import com.release.spbook.DI.DaggerMainComponent
import com.release.spbook.DI.MainComponent
import com.release.spbook.DI.module.DataModule
import com.release.spbook.DI.module.MapModule

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