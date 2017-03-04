package com.adamg.hnreader.views.listfragments

import com.adamg.hnreader.views.listfragments.ItemsModel
import com.hannesdorfmann.mosby.mvp.MvpView

interface ItemsView : MvpView {

    fun render(viewState: ItemsModel)
}