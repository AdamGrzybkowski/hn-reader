package com.adamg.hnreader.views.comments

import com.hannesdorfmann.mosby.mvp.MvpView

interface CommentsView: MvpView {
    fun render(model: CommentsModel)
}