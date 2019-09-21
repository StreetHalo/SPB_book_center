package com.release.spbook.DI.module

import android.content.Context
import com.release.spbook.PLACES_URL
import com.release.spbook.interactors.AuthorsInteractor
import com.release.spbook.interactors.PlacesInteractor
import com.release.spbook.presenter.SplashPresenter
import com.release.spbook.repository.AuthorsRepository
import com.release.spbook.repository.BackendPlacesRepository
import com.release.spbook.repository.PlacesRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
open class DataModule(private val context: Context) {


    @Provides
    fun provideSplashPresenter(placesInteractor: PlacesInteractor, authorsInteractor: AuthorsInteractor): SplashPresenter {
        return object : SplashPresenter(placesInteractor,authorsInteractor){}
    }


    @Provides
    fun providePlacesInteractor(backendPlacesRepository: BackendPlacesRepository, placesRepository: PlacesRepository): PlacesInteractor {
        return object : PlacesInteractor(backendPlacesRepository, placesRepository) {}
    }

    @Provides
    fun providePublishRepository(gson: Gson):PlacesRepository{
        return object : PlacesRepository(gson, context){}
    }


    @Provides
    fun provideAuthorsInteractor(authorsFromLocal: AuthorsRepository): AuthorsInteractor {
        return AuthorsInteractor(authorsFromLocal)
    }

    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    fun provideAuthorsFromLocal(gson: Gson):AuthorsRepository {

        return  object : AuthorsRepository(gson,context){}
    }

    @Provides
    fun provideBackEndPlacesRepository(retrofit: Retrofit):BackendPlacesRepository{
        return BackendPlacesRepository(retrofit)
    }

    @Provides
    fun providePlacesRetrofit():Retrofit{
        return   Retrofit.Builder()
            .baseUrl(PLACES_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    }

}