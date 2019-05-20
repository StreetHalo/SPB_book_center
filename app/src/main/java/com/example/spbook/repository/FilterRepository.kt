package com.example.spbook.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.spbook.PUBLISH_KEY_FILTER_STORE

class FilterRepository(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("FILTER", Context.MODE_PRIVATE)

    fun isCheckPublishStore():Boolean{
        return sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_STORE,true)
    }


}