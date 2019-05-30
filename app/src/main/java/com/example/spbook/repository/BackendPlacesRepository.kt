package com.example.spbook.repository

import android.util.Log
import com.example.spbook.entities.POJO.BookStore
import com.example.spbook.entities.POJO.Place
import com.example.spbook.entities.POJO.Publish
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.create

class BackendPlacesRepository(private val retrofit: Retrofit) {
    private val placesApi = retrofit.create(PlacesApi::class.java)


    fun getPublishPlaces(): Observable<ArrayList<Publish>> {
       return placesApi.getPublishList()
    }

    fun getStorePlaces(): Observable<ArrayList<BookStore>> {
        return placesApi.getBookStoreList()
    }
}