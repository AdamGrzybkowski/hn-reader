package com.adamg.hnreader.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

data class Item(
        val id: Long,
        val title: String,
        val points: Int,
        val user: String,
        val time: Long,
        val timeAgo: String,
        val commentsCount: Int,
        val type: Type,
        val url: String,
        val domain: String,
        val comments: List<Comment>): Parcelable{
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Item> = object : Parcelable.Creator<Item> {
            override fun createFromParcel(source: Parcel): Item = Item(source)
            override fun newArray(size: Int): Array<Item?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readLong(), source.readString(), source.readInt(), source.readString(), source.readLong(), source.readString(), source.readInt(), Type.values()[source.readInt()], source.readString(), source.readString(), ArrayList<Comment>().apply{ source.readList(this, Comment::class.java.classLoader) })

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeLong(id)
        dest?.writeString(title)
        dest?.writeInt(points)
        dest?.writeString(user)
        dest?.writeLong(time)
        dest?.writeString(timeAgo)
        dest?.writeInt(commentsCount)
        dest?.writeInt(type.ordinal)
        dest?.writeString(url)
        dest?.writeString(domain)
        dest?.writeList(comments)
    }
}

enum class Type {
    @SerializedName("job")
    JOB,
    @SerializedName("story")
    STORY,
    @SerializedName("ask")
    ASK,
    @SerializedName("link")
    LINK
}