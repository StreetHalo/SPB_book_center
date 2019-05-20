package com.example.spbook.interactors

import com.example.spbook.POJO.BookStore
import com.example.spbook.POJO.Publish
import com.example.spbook.repository.PlacesRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class  PlacesInteractor(private val publishRepository: PlacesRepository) {


    
    fun getListPublish(): Single<ArrayList<Publish>> {
        return publishRepository.getPublishFromRepository()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
    }

    fun getListBookStores():Single<ArrayList<BookStore>>{
        return publishRepository.getBooksFromRepository()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
    }

}