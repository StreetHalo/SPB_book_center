package com.example.spbook.entities.POJO

import android.os.Parcelable
import com.google.maps.android.clustering.ClusterItem

abstract class Place: ClusterItem,Parcelable {

    abstract val timeWork: String
    abstract val address: String
    abstract val  about: String
    abstract  val category: String
    abstract  val email: String
    abstract  val id:    String
    abstract  val latitude: Double
    abstract  val longitude: Double
    abstract  val name: String

    abstract val images: List<String>
    abstract val placeUrl: String
    abstract val telNumber: String

    abstract fun getIcon(): Int

    abstract fun getColor(): Int

    abstract fun getAddressAndName(): String

    abstract override fun getTitle(): String

    abstract override fun getSnippet(): String

    abstract fun isService1(): Boolean

    abstract fun isService2(): Boolean

    abstract fun isService3(): Boolean

    abstract fun getService1Icon():Int

    abstract fun getService2Icon():Int

    abstract fun getService3Icon():Int

    abstract fun aboutService1(): Int

    abstract fun aboutService2(): Int

    abstract fun aboutService3(): Int

    abstract fun getThemeProfile():Int
}