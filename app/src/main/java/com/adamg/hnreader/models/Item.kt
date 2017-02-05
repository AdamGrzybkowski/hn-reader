package com.adamg.hnreader.models

data class Item (
        val id: Long,
        val deleted: Boolean,
        val type: ItemType,
        val by: String,
        val time: Long,
        val text: String,
        val dead: Boolean,
        val parent: Item,
        val kids: List<Long>,
        val url: String,
        val score: Int,
        val title: String,
        val parts: List<Item>,
        val descendants: Int
)

enum class ItemType {
    JOB, STORY, COMMENT, POLL, POLLOPT
}