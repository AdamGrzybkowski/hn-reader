package com.adamg.hnreader.views.newstories

import android.os.Parcel
import android.os.Parcelable
import com.adamg.hnreader.models.Story
import java.util.*

sealed class NewStoriesModel: Parcelable{
    class Error(val error: String): NewStoriesModel(){
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

    class Loading(): NewStoriesModel(){
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

    class EmptyResult(): NewStoriesModel(){
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<EmptyResult> = object : Parcelable.Creator<EmptyResult> {
                override fun createFromParcel(source: Parcel): EmptyResult = EmptyResult(source)
                override fun newArray(size: Int): Array<EmptyResult?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel) : this()

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {}
    }

    class Result(val stories: List<Story>): NewStoriesModel(){
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<Result> = object : Parcelable.Creator<Result> {
                override fun createFromParcel(source: Parcel): Result = Result(source)
                override fun newArray(size: Int): Array<Result?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel) : this(ArrayList<Story>().apply{ source.readList(this, Story::class.java.classLoader) })

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeList(stories)
        }
    }
}