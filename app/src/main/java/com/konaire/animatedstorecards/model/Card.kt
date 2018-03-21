package com.konaire.animatedstorecards.model

import android.os.Parcel
import android.os.Parcelable

import com.konaire.animatedstorecards.ui.list.ListItemType
import com.konaire.animatedstorecards.ui.list.ViewType
import com.konaire.animatedstorecards.util.createParcel

/**
 * Created by Evgeny Eliseyev on 20/03/2018.
 */

data class Card(
    val photo: String,
    val title: String,
    val shortDescription: String,
    val fullDescription: String
): Parcelable, ViewType {
    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { Card(it) }
    }

    constructor(): this("", "", "", "")

    constructor(parcel: Parcel): this(
        photo = parcel.readString() ?: "",
        title = parcel.readString() ?: "",
        shortDescription = parcel.readString() ?: "",
        fullDescription = parcel.readString() ?: ""
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(photo)
        parcel?.writeString(title)
        parcel?.writeString(shortDescription)
        parcel?.writeString(fullDescription)
    }

    override fun getViewType(): Int = ListItemType.Card.ordinal
}