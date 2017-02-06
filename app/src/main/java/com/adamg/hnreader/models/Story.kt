package com.adamg.hnreader.models

import com.google.gson.annotations.SerializedName
import org.w3c.dom.Comment

data class Story(
        val id: Long,
        val title: String,
        val points: Int,
        val user: String,
        val time: Long,
        @SerializedName("time_ago") val timeAgo: String,
        @SerializedName("comments_count") val commentsCount: Int,
        val type: ItemType,
        val url: String,
        val domain: String,
        val comments: List<Comment>
)

enum class ItemType {
    JOB, STORY, COMMENT, LINK
}