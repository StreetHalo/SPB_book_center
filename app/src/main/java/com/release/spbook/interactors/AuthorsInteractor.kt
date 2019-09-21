package com.release.spbook.interactors

import com.release.spbook.entities.POJO.AuthorsQuote
import com.release.spbook.repository.AuthorsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthorsInteractor(val gson: AuthorsRepository) {

fun getListAuthors(): Observable<List<AuthorsQuote>> {

   return gson.getAuthorsFromLocalJSON()
       .observeOn(AndroidSchedulers.mainThread())
       .subscribeOn(Schedulers.io())
    }
}