package com.release.spbook.entities.POJO

import android.os.Parcel
import android.os.Parcelable
import com.release.spbook.R
import com.google.android.gms.maps.model.LatLng

data class Library(
    override val about: String,
    override val address: String,
    override val category: String,
    val hasCoworking: Boolean,
    val hasPrinter: Boolean,
    val hasWiFi: Boolean,
    override val id: String,
    override val images: List<String>,
    override val latitude: Double,
    override val longitude: Double,
    override val name: String,
    override val placeUrl: String,
    override val telNumber: String,
    override val timeWork: String
): Place() {
    override val email: String = "-"

    constructor(parcel: Parcel) : this(
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
        parcel.readString()
    ) {
    }


    override fun getIcon(): Int {
        return R.drawable.ic_local_library_red
    }

    override fun getColor(): Int {
        return R.color.lib_color
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
        return hasPrinter
    }

    override fun isService2(): Boolean {
        return hasWiFi
    }

    override fun isService3(): Boolean {
        return hasCoworking
    }

    override fun getService1Icon(): Int {
        return R.drawable.ic_print_black_24dp
    }

    override fun getService2Icon(): Int {
        return R.drawable.ic_wifi_black_24dp
    }

    override fun getService3Icon(): Int {
        return R.drawable.ic_desktop_windows_black_24dp
    }

    override fun aboutService1(): Int {
        return R.string.fil_lib_print
    }

    override fun aboutService2(): Int {
        return R.string.fil_lib_wifi
    }

    override fun aboutService3(): Int {
        return R.string.fil_lib_coworking
    }

    override fun getThemeProfile(): Int {
        return R.style.ProfileLibTheme
    }

    override fun getPosition(): LatLng {
        return LatLng(latitude, longitude)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
       // super.writeToParcel(parcel, flags)
        parcel.writeString(about)
        parcel.writeString(address)
        parcel.writeString(category)
        parcel.writeByte(if (hasCoworking) 1 else 0)
        parcel.writeByte(if (hasPrinter) 1 else 0)
        parcel.writeByte(if (hasWiFi) 1 else 0)
        parcel.writeString(id)
        parcel.writeStringList(images)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(name)
        parcel.writeString(placeUrl)
        parcel.writeString(telNumber)
        parcel.writeString(timeWork)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Library> {
        override fun createFromParcel(parcel: Parcel): Library {
            return Library(parcel)
        }

        override fun newArray(size: Int): Array<Library?> {
            return arrayOfNulls(size)
        }
    }
}