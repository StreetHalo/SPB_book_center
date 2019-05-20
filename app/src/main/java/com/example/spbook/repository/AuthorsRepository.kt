package com.example.spbook.repository

import android.content.Context
import com.example.spbook.POJO.AuthorsQuote
import com.example.spbook.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.Single
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader

open class AuthorsRepository(private  val gson: Gson, private  val context: Context) {


    fun getAuthorsFromLocalJSON(): Observable<List<AuthorsQuote>> {
          return Observable.fromCallable {
            val raw = context.resources.openRawResource(R.raw.authors)
            val listType = object : TypeToken<ArrayList<AuthorsQuote>>() {}.type
            val rd = BufferedReader(InputStreamReader(raw) as Reader?)
            return@fromCallable gson.fromJson(rd,listType) as List<AuthorsQuote>
        }
    }
}