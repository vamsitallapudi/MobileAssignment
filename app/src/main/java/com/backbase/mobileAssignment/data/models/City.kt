package com.backbase.mobileAssignment.data.models

import android.os.Parcel
import android.os.Parcelable
import com.backbase.mobileAssignment.utils.StringUtils
import com.squareup.moshi.Json

data class City(
    @Json(name = "name") val name: String,
    @Json(name = "country") var country: String,
    @Json(name = "_id") var id: Long,
    @Json(name = "coord") var coordinates: Coordinates,
    var normalizedStr: String = StringUtils.normalizeStr(name + country)
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readParcelable(Coordinates::class.java.classLoader)!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(country)
        parcel.writeLong(id)
        parcel.writeParcelable(coordinates, flags)
        parcel.writeString(normalizedStr)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }
}
