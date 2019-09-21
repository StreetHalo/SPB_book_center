package com.release.spbook.DI

import com.release.spbook.DI.module.DataModule
import com.release.spbook.DI.module.MapModule
import com.release.spbook.view.MapActivity
import com.release.spbook.view.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, MapModule::class])
interface MainComponent {

    fun inject(app: SplashActivity)

    fun inject(app: MapActivity)
}