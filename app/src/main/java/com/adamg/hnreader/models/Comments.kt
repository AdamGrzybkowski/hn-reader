package com.adamg.hnreader.models

import com.google.gson.annotations.SerializedName

data class Comments (
        val id: Long,
        val level: Int,
        val user: String,
        val time: Long,
        @SerializedName("time_ago") val time_ago: String,
        val content: String,
        val comments: List<Comments>
)