package com.example.spbook.interactors

import com.example.spbook.BOOK_STORES_JSON
import com.example.spbook.PUBLISH_JSON
import com.example.spbook.entities.POJO.BookStore
import com.example.spbook.entities.POJO.Library
import com.example.spbook.entities.POJO.Place
import com.example.spbook.entities.POJO.Publish
import com.example.spbook.repository.BackendPlacesRepository
import com.example.spbook.repository.PlacesRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class  PlacesInteractor(private val placesRepository: BackendPlacesRepository, private val publishRepository: PlacesRepository) {


    fun getListPublish(): Observable<ArrayList<Publish>> {
        return placesRepository.getPublishPlaces()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getListBookStores():Observable<ArrayList<BookStore>>{
        return placesRepository.getStorePlaces()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getListLibs():Single<ArrayList<Library>>{
        return publishRepository.getLibsFromRepository()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }



}