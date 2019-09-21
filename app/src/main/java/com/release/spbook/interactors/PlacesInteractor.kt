package com.release.spbook.interactors

import com.release.spbook.entities.POJO.BookStore
import com.release.spbook.entities.POJO.Library
import com.release.spbook.entities.POJO.Publish
import com.release.spbook.repository.BackendPlacesRepository
import com.release.spbook.repository.PlacesRepository
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

    fun getListLibs(): Observable<ArrayList<Library>> {
        return placesRepository.getLibPlaces()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}