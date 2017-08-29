package com.adamg.hnreader.data.dao

import com.adamg.hnreader.models.Comment
import com.vicpin.krealmextensions.queryAsObservable
import rx.Observable
import javax.inject.Inject

class CommentDao @Inject constructor() {

    fun getComments(parentId: Long) =
            Comment().queryAsObservable { query -> query.equalTo("parentItem.id", parentId) }

    fun getComment(commentId: Long): Observable<Comment> {
        return Comment().queryAsObservable { query -> query.equalTo("id", commentId) }
                .map { it.first() }
    }

}