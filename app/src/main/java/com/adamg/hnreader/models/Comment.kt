package com.adamg.hnreader.models

data class Comment(
        val id: Long,
        val level: Int,
        val user: String,
        val time: Long,
        val time_ago: String,
        val content: String,
        val comments: List<Comment>
)