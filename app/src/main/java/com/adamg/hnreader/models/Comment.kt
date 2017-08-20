package com.adamg.hnreader.models

data class Comment(
        val id: Long,
        val level: Int,
        val user: String,
        val time: Long,
        val timeAgo: String,
        val content: String,
        val comments: List<Comment>
)