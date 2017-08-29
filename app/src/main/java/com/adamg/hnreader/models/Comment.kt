package com.adamg.hnreader.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Comment(
        @PrimaryKey var id: Long = 0,
        var level: Int = 0,
        var user: String? = null,
        var time: Long? = null,
        var time_ago: String? = null,
        var content: String = "",
        var comments: RealmList<Comment> = RealmList()): RealmObject()