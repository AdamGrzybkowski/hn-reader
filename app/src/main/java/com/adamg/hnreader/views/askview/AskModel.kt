package com.adamg.hnreader.views.askview

import android.os.Parcel
import android.os.Parcelable
import com.adamg.hnreader.models.Ask
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.views.listfragments.ItemsModel
import java.util.*

sealed class AskModel : Parcelable {
    class Error(val error: String): AskModel(){
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<Error> = object : Parcelable.Creator<Error> {
                override fun createFromParcel(source: Parcel): Error = Error(source)
                override fun newArray(size: Int): Array<Error?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel) : this(source.readString())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(error)
        }
    }

    class Loading(): AskModel(){
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<Loading> = object : Parcelable.Creator<Loading> {
                override fun createFromParcel(source: Parcel): Loading = Loading(source)
                override fun newArray(size: Int): Array<Loading?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel) : this()

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {}
    }

}