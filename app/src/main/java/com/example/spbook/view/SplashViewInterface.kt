package com.example.spbook.view

import com.example.spbook.POJO.BookStore
import com.example.spbook.POJO.Publish

interface SplashViewInterface {

   fun setText(author:String, citation:String)

    fun startMapActivity()

    fun setPublishList(listPublish: ArrayList<Publish>)

    fun setBookStoresList(listBookStores:ArrayList<BookStore>)
}