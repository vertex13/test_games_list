package com.github.vertex13.gameslist.presentation.gamedetails

import android.os.Parcel
import android.os.Parcelable
import com.github.vertex13.gameslist.domain.Game

class ParcelableGame : Game, Parcelable {

    constructor(id: Long, name: String, imageUrl: String, rating: Float)
            : super(id, name, imageUrl, rating)

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.apply {
            writeLong(id)
            writeString(name)
            writeString(imageUrl)
            writeFloat(rating)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelableGame> {
        override fun createFromParcel(parcel: Parcel): ParcelableGame {
            return ParcelableGame(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableGame?> {
            return arrayOfNulls(size)
        }
    }
}

fun Game.toParcelable() = ParcelableGame(id, name, imageUrl, rating)
