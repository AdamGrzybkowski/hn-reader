package com.adamg.hnreader.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Item(
        @PrimaryKey var id: Long = 0,
        var title: String? = null,
        var points: Int? = null,
        var user: String? = null,
        var time: Long? = 0,
        var time_ago: String? = null,
        var comments_count: Int = 0,
        var type: String = "link",
        var url: String? = null,
        var content: String? = null,
        var domain: String? = null,
        var comments: RealmList<Comment> = RealmList()): RealmObject()