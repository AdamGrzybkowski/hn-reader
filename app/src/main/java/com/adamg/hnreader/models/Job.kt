package com.adamg.hnreader.models

import android.os.Parcel
import android.os.Parcelable

data class Job(
        val id: Long,
        val title: String,
        val time: Long,
        val timeAgo: String,
        val url: String,
        val domain: String?): Parcelable{
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Job> = object : Parcelable.Creator<Job> {
            override fun createFromParcel(source: Parcel): Job = Job(source)
            override fun newArray(size: Int): Array<Job?> = arrayOfNulls(size)
        }

        fun fromItem(item: Item): Job{
            return Job(item.id, item.title, item.time, item.timeAgo, item.url, item.domain)
        }
    }

    constructor(source: Parcel) : this(source.readLong(), source.readString(), source.readLong(), source.readString(), source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeLong(id)
        dest?.writeString(title)
        dest?.writeLong(time)
        dest?.writeString(timeAgo)
        dest?.writeString(url)
        dest?.writeString(domain)
    }
}