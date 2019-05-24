package com.example.spbook.use_cases

import android.content.Context
import android.util.Log
import com.example.spbook.*
import com.example.spbook.entities.POJO.Place
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class PlaceFilter(val context: Context) {
    private var isLib: Boolean = false
    private var isWiFiLib:Boolean = false
    private var isPrintLib:Boolean = false
    private var isCoworkingLib:Boolean = false
    private var isEmptyLib:Boolean = false

    private var isBooks: Boolean = false
    private var isWiFiBooks:Boolean = false
    private var isCafeBooks:Boolean = false
    private var isCoworkingBooks:Boolean = false
    private var isEmptyBook:Boolean = false

    private var isPublish: Boolean = false
    private var isYourBookPublish:Boolean = false
    private var isStorePublish:Boolean = false
    private var isOnlineStorePublish:Boolean = false
    private var isEmptyPub:Boolean = false




    fun filterPublishList(list: ArrayList<Place>): ArrayList<Place> {
        val filterList = ArrayList<Place>()

        if (isPublish) {

              for (a in list) {

                if(!isYourBookPublish && !isStorePublish && !isOnlineStorePublish){
                    filterList.add(a)
                }
                else{
                    if (isYourBookPublish and a.isService1()){
                        filterList.add(a)
                        continue
                    }
                    if(isStorePublish and a.isService2()){
                        filterList.add(a)
                        continue
                    }
                    if(isOnlineStorePublish and a.isService3()){
                        filterList.add(a)
                        continue
                    }
                }
            }
        }

        return filterList
    }


    fun filterStoreListList(list: ArrayList<Place>): ArrayList<Place> {
        val filterList = ArrayList<Place>()

        if (isBooks) {

            for (a in list) {

                if(!isCafeBooks && !isCoworkingBooks && !isWiFiBooks){
                    filterList.add(a)
                }

                else{
                    if (isCafeBooks and a.isService1()){
                        filterList.add(a)
                        continue
                    }
                    if(isCoworkingBooks and a.isService2()){
                        filterList.add(a)
                        continue
                    }
                    if(isWiFiBooks and a.isService3()){
                        filterList.add(a)
                        continue
                    }
                }
            }
        }

        return filterList
    }




    fun filterLibListList(list: ArrayList<Place>): ArrayList<Place> {
        val filterList = ArrayList<Place>()

        if (isLib) {

            for (a in list) {

                if(!isPrintLib && !isWiFiLib && !isCoworkingLib){
                    filterList.add(a)
                }

                else{
                    if (isPrintLib and a.isService1()){
                        filterList.add(a)
                        continue
                    }
                    if(isWiFiLib and a.isService2()){
                        filterList.add(a)
                        continue
                    }
                    if(isCoworkingLib and a.isService3()){
                        filterList.add(a)
                        continue
                    }
                }
            }
        }

        return filterList
    }

    fun setFilter() {
        val sharedPreferences = context.getSharedPreferences("FILTER", Context.MODE_PRIVATE)

        isLib = sharedPreferences.getBoolean(LIB_KEY_FILTER_GROUP, true)
        isWiFiLib = sharedPreferences.getBoolean(LIB_KEY_FILTER_WIFI, false)
        isPrintLib = sharedPreferences.getBoolean(LIB_KEY_FILTER_PRINT, false)
        isCoworkingLib = sharedPreferences.getBoolean(LIB_KEY_FILTER_COWORKING, false)

        isBooks = sharedPreferences.getBoolean(BOOK_KEY_FILTER_GROUP, true)
        isWiFiBooks = sharedPreferences.getBoolean(BOOK_KEY_FILTER_WIFI, false)
        isCoworkingBooks = sharedPreferences.getBoolean(BOOK_KEY_FILTER_COWORKING, false)
        isCafeBooks = sharedPreferences.getBoolean(BOOK_KEY_FILTER_CAFE, false)

        isPublish = sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_GROUP, true)
        isYourBookPublish = sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_YOUR_BOOK, false)
        isStorePublish = sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_STORE, false)
        isOnlineStorePublish = sharedPreferences.getBoolean(PUBLISH_KEY_FILTER_ONLINE_STORE, false)
    }
}