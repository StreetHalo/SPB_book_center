package com.example.spbook.DI.module

import android.content.Context
import com.example.spbook.interactors.AuthorsInteractor
import com.example.spbook.interactors.PlacesInteractor
import com.example.spbook.presenter.SplashPresenter
import com.example.spbook.repository.AuthorsRepository
import com.example.spbook.repository.PlacesRepository
import com.example.spbook.view.CustomAdapter
import dagger.Module
import dagger.Provides
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import javax.inject.Singleton



@Module
open class DataModule(private val context: Context) {


    @Singleton
    @Provides
    fun provideSplashPresenter(placesInteractor: PlacesInteractor, authorsInteractor: AuthorsInteractor): SplashPresenter {
        return object : SplashPresenter(placesInteractor,authorsInteractor){}
    }

    @Singleton
    @Provides
    fun providePlacesInteractor(publishRepository: PlacesRepository): PlacesInteractor {
        return object : PlacesInteractor(publishRepository) {}
    }

    @Singleton
    @Provides
    fun providePublishRepository(gson: Gson):PlacesRepository{
        return object : PlacesRepository(gson, context){}
    }


    @Singleton
    @Provides
    fun provideAuthorsInteractor(authorsFromLocal: AuthorsRepository): AuthorsInteractor {
        return AuthorsInteractor(authorsFromLocal)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun provideAuthorsFromLocal(gson: Gson):AuthorsRepository {

        return  object : AuthorsRepository(gson,context){}
    }

}