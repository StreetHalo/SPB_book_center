package com.example.spbook.POJO

import android.os.Parcel
import android.os.Parcelable
import com.example.spbook.R
import com.google.android.gms.maps.model.LatLng

data class BookStore(
    override  val about: String,
    override  val address: String,
    override  val category: String,
    val hasCafe: Boolean,
    val hasCoworking: Boolean,
    val hasWiFi: Boolean,
    override  val id: String,
    override  val latitude: Double,
    override  val longitude: Double,
    override  val name: String,
    override val placeUrl: String,
    override val telNumber: String,
    override  val timeWork: String,
    override val hasOnLineStore: Boolean,
    override val hasPublYourBooks: Boolean,
    override val hasStore: Boolean,
    override val images: List<String>

): Place(){
    override fun aboutService1(): Int {
        return R.string.fil_books_cafe
    }

    override fun aboutService2(): Int {
        return R.string.fil_books_coworking
    }

    override fun aboutService3(): Int {
        return R.string.fil_books_wifi
    }


    override val email: String = ""

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.createStringArrayList()
    ) {
    }


    override fun getIcon(): Int {
        return R.drawable.ic_book_store_brown
    }

    override fun getAddressAndName(): String {
        return "$name $address"
    }

    override fun getTitle(): String {
        return name
    }

    override fun getSnippet(): String {
        return about
    }

    override fun isService1(): Boolean {
        return hasCafe
    }

    override fun isService2(): Boolean {
        return hasCoworking
    }

    override fun isService3(): Boolean {
        return hasWiFi
    }

    override fun getService1Icon(): Int {
        return R.drawable.ic_local_cafe_black_24dp
    }

    override fun getService2Icon(): Int {
        return R.drawable.ic_desktop_windows_black_24dp
    }

    override fun getService3Icon(): Int {
        return R.drawable.ic_wifi_black_24dp
    }

    override fun getPosition(): LatLng {
        return LatLng(latitude, longitude)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //super.writeToParcel(parcel, flags)
        parcel.writeString(about)
        parcel.writeString(address)
        parcel.writeString(category)
        parcel.writeByte(if (hasCafe) 1 else 0)
        parcel.writeByte(if (hasCoworking) 1 else 0)
        parcel.writeByte(if (hasWiFi) 1 else 0)
        parcel.writeString(id)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(name)
        parcel.writeString(placeUrl)
        parcel.writeString(telNumber)
        parcel.writeString(timeWork)
        parcel.writeByte(if (hasOnLineStore) 1 else 0)
        parcel.writeByte(if (hasPublYourBooks) 1 else 0)
        parcel.writeByte(if (hasStore) 1 else 0)
        parcel.writeStringList(images)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookStore> {
        override fun createFromParcel(parcel: Parcel): BookStore {
            return BookStore(parcel)
        }

        override fun newArray(size: Int): Array<BookStore?> {
            return arrayOfNulls(size)
        }
    }


}