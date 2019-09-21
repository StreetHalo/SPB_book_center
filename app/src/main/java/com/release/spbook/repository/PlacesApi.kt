package com.release.spbook.repository

import com.release.spbook.entities.POJO.BookStore
import com.release.spbook.entities.POJO.Library
import com.release.spbook.entities.POJO.Publish
import io.reactivex.Observable
import retrofit2.http.GET

interface PlacesApi {
    @GET ("/bspb/json/publish.json")
    fun getPublishList(): Observable<ArrayList<Publish>>

    @GET ("/bspb/json/bookstores.json")
    fun getBookStoreList(): Observable<ArrayList<BookStore>>

    @GET ("/bspb/json/libs.json")
    fun getLibsList(): Observable<ArrayList<Library>>

}