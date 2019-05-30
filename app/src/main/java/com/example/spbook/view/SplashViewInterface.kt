package com.example.spbook.view

import com.example.spbook.entities.POJO.BookStore
import com.example.spbook.entities.POJO.Library
import com.example.spbook.entities.POJO.Place
import com.example.spbook.entities.POJO.Publish

interface SplashViewInterface {

   fun setText(author:String, citation:String)

    fun startMapActivity()

    fun setPublishList(listPublish: ArrayList<Publish>)

    fun setBookStoresList(listBookStores:ArrayList<BookStore>)

    fun setLibsList(listLibs:ArrayList<Library>)

    fun errorFromService()
}