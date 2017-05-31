package com.adamg.hnreader.models

import android.os.Parcel
import android.os.Parcelable
import com.adamg.hnreader.R
import java.util.*

data class Ask(
        val id: Long,
        val title: String,
        val points: Int,
        val user: String?,
        val time: Long,
        val timeAgo: String,
        val commentsCount: Int,
        val content: String?,
        val comments: List<Comment>): Parcelable{
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Ask> = object : Parcelable.Creator<Ask> {
            override fun createFromParcel(source: Parcel): Ask = Ask(source)
            override fun newArray(size: Int): Array<Ask?> = arrayOfNulls(size)
        }

        fun fromItem(item: Item): Ask{
            return Ask(item.id, item.title, item.points, item.user, item.time, item.timeAgo,
                    item.commentsCount, "", listOf())
        }
    }

    constructor(source: Parcel) : this(source.readLong(), source.readString(), source.readInt(), source.readString(), source.readLong(), source.readString(), source.readInt(), source.readString(), ArrayList<Comment>().apply{ source.readList(this, Comment::class.java.classLoader) })

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeLong(id)
        dest?.writeString(title)
        dest?.writeInt(points)
        dest?.writeString(user)
        dest?.writeLong(time)
        dest?.writeString(timeAgo)
        dest?.writeInt(commentsCount)
        dest?.writeString(content)
        dest?.writeList(comments)
    }
}