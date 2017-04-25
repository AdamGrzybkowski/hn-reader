package com.adamg.hnreader.views.askview

import android.os.Parcel
import android.os.Parcelable
import com.adamg.hnreader.models.Ask
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.views.listfragments.ItemsModel
import java.util.*

sealed class AskModel : Parcelable {
    class Error(val error: String?): AskModel(), Parcelable {
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

    class Result(val ask: Ask): AskModel(), Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<Result> = object : Parcelable.Creator<Result> {
                override fun createFromParcel(source: Parcel): Result = Result(source)
                override fun newArray(size: Int): Array<Result?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel) : this(source.readParcelable<Ask>(Ask::class.java.classLoader))

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeParcelable(ask, 0)
        }
    }

}