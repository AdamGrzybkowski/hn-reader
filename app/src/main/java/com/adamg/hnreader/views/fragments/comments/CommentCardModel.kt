package com.adamg.hnreader.views.fragments.comments

import com.adamg.hnreader.models.Comment

class CommentCardModel(val comment: Comment, var isExpanded: Boolean = false) {

    override fun equals(other: Any?): Boolean {
        val commentCardModel = other as CommentCardModel
        return comment == commentCardModel.comment
    }

    override fun hashCode(): Int{
        var result = comment.hashCode()
        result = 31 * result + isExpanded.hashCode()
        return result
    }
}