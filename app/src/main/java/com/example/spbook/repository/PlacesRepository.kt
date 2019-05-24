package com.example.spbook.repository

import android.content.Context
import com.example.spbook.entities.POJO.BookStore
import com.example.spbook.entities.POJO.Library
import com.example.spbook.entities.POJO.Publish
import com.example.spbook.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader

open class PlacesRepository (private  val gson: Gson, private  val context: Context){

    fun getPublishFromRepository(): Single<ArrayList<Publish>> {
        return Single.fromCallable {
            val raw = context.resources.openRawResource(R.raw.publish)
            val listType = object : TypeToken<ArrayList<Publish>>() {}.type
            val rd = BufferedReader(InputStreamReader(raw) as Reader?)
            return@fromCallable gson.fromJson(rd,listType) as ArrayList<Publish>
        }
    }

    fun getBooksFromRepository(): Single<ArrayList<BookStore>> {
        return Single.fromCallable {
            val raw = context.resources.openRawResource(R.raw.bookstores)
            val listType = object : TypeToken<ArrayList<BookStore>>() {}.type
            val rd = BufferedReader(InputStreamReader(raw) as Reader?)
            return@fromCallable gson.fromJson(rd,listType) as ArrayList<BookStore>
        }
    }

    fun getLibsFromRepository(): Single<ArrayList<Library>> {
        return Single.fromCallable {
            val raw = context.resources.openRawResource(R.raw.libs)
            val listType = object : TypeToken<ArrayList<Library>>() {}.type
            val rd = BufferedReader(InputStreamReader(raw) as Reader?)
            return@fromCallable gson.fromJson(rd,listType) as ArrayList<Library>
        }
    }

}