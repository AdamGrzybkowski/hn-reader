package com.adamg.hnreader.views.askview

import com.hannesdorfmann.mosby3.mvp.MvpView

interface AskView : MvpView {
    fun render(askModel: AskModel)
}