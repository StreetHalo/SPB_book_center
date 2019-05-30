package com.example.spbook.repository

import com.example.spbook.entities.POJO.BookStore
import com.example.spbook.entities.POJO.Place
import com.example.spbook.entities.POJO.Publish
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PlacesApi {
    @GET ("/bspb/json/plish.json")
    fun getPublishList(): Observable<ArrayList<Publish>>

    @GET ("/bspb/json/bookstores.json")
    fun getBookStoreList(): Observable<ArrayList<BookStore>>

}