package com.konaire.animatedstorecards.util

import android.app.Activity
import android.graphics.Point
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Evgeny Eliseyev on 20/03/2018.
 */

inline fun <reified T : Parcelable> createParcel(crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
    object: Parcelable.Creator<T> {
        override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
        override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
    }

fun Activity.getScreenHeight(): Int {
    val size = Point()
    windowManager.defaultDisplay.getSize(size)

    return size.y
}