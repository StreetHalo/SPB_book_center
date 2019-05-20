package com.example.spbook.interactors

import com.example.spbook.POJO.AuthorsQuote
import com.example.spbook.repository.AuthorsRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthorsInteractor(val gson: AuthorsRepository) {

fun getListAuthors(): Observable<List<AuthorsQuote>> {

   return gson.getAuthorsFromLocalJSON()
       .observeOn(Schedulers.io())
       .subscribeOn(AndroidSchedulers.mainThread())
    }
}