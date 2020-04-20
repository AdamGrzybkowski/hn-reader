package com.adamg.hnreader.views.fragments.comments

import com.hannesdorfmann.mosby3.mvp.MvpView

interface CommentsView: MvpView {
    fun render(model: CommentsModel)
}