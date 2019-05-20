package com.example.spbook.DI

import com.example.spbook.DI.module.DataModule
import com.example.spbook.DI.module.MapModule
import com.example.spbook.view.MapActivity
import com.example.spbook.view.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, MapModule::class])
interface MainComponent {

    fun inject(app: SplashActivity)

    fun inject(app: MapActivity)
}