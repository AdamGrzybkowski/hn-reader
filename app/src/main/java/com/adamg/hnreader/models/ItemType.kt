package com.adamg.hnreader.models

import com.google.gson.annotations.SerializedName

enum class ItemType {
    @SerializedName("job")
    JOB,
    @SerializedName("story")
    STORY,
    @SerializedName("job")
    ASK,
    @SerializedName("link")
    LINK
}