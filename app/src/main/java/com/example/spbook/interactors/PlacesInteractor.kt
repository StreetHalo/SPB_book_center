package com.example.spbook.interactors

import com.example.spbook.entities.POJO.BookStore
import com.example.spbook.entities.POJO.Library
import com.example.spbook.entities.POJO.Publish
import com.example.spbook.repository.PlacesRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class  PlacesInteractor(private val publishRepository: PlacesRepository) {


    
    fun getListPublish(): Single<ArrayList<Publish>> {
        return publishRepository.getPublishFromRepository()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getListBookStores():Single<ArrayList<BookStore>>{
        return publishRepository.getBooksFromRepository()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getListLibs():Single<ArrayList<Library>>{
        return publishRepository.getLibsFromRepository()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }



}