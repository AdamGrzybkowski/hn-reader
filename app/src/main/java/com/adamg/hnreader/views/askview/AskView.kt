package com.adamg.hnreader.views.askview

import com.hannesdorfmann.mosby.mvp.MvpView

interface AskView : MvpView {
    fun render(askModel: AskModel): AskModel
}