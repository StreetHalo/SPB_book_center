package com.example.spbook.POJO

import android.os.Parcel
import android.os.Parcelable
import com.example.spbook.R
import com.google.android.gms.maps.model.LatLng

open class Publish
(
    override val about: String,
    override val address: String,
    override val category: String,
    override val email: String,
    override val hasOnLineStore: Boolean,
    override val hasPublYourBooks: Boolean,
    override val hasStore: Boolean,
    override val id: String,
    override val images: List<String>,
    override val latitude: Double,
    override val longitude: Double,
    override val name: String,
    override val placeUrl: String,
    val storeOnlineUrl: String,
    override val telNumber: String,
    override val timeWork: String
): Place() {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun aboutService1(): Int {
        return R.string.image_profile_publish
    }

    override fun aboutService2(): Int {
        return R.string.image_profile_store
    }

    override fun aboutService3(): Int {
        return R.string.image_profile_online_store
    }




    override fun isService1(): Boolean {
        return hasPublYourBooks
    }

    override fun isService2(): Boolean {
        return hasStore
    }

    override fun isService3(): Boolean {
        return hasOnLineStore
    }

    override fun getService1Icon(): Int {
            return R.drawable.ic_local_offer_black_24dp
    }

    override fun getService2Icon(): Int {
        return R.drawable.ic_store_black_24dp
    }

    override fun getService3Icon(): Int {
        return R.drawable.ic_dvr_black_24dp
    }




    override fun getIcon(): Int {
  return R.drawable.ic_local_publish_green
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

 override fun getPosition(): LatLng {
     return LatLng(latitude, longitude)
   }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
      //  super.writeToParcel(parcel, flags)
        parcel.writeString(about)
        parcel.writeString(address)
        parcel.writeString(category)
        parcel.writeString(email)
        parcel.writeByte(if (hasOnLineStore) 1 else 0)
        parcel.writeByte(if (hasPublYourBooks) 1 else 0)
        parcel.writeByte(if (hasStore) 1 else 0)
        parcel.writeString(id)
        parcel.writeStringList(images)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(name)
        parcel.writeString(placeUrl)
        parcel.writeString(storeOnlineUrl)
        parcel.writeString(telNumber)
        parcel.writeString(timeWork)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Publish> {
        override fun createFromParcel(parcel: Parcel): Publish {
            return Publish(parcel)
        }

        override fun newArray(size: Int): Array<Publish?> {
            return arrayOfNulls(size)
        }
    }


}