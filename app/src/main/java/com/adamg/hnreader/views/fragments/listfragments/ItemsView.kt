package com.adamg.hnreader.views.fragments.listfragments

import com.hannesdorfmann.mosby3.mvp.MvpView

interface ItemsView : MvpView {
    fun render(itemsUiModel: ItemsUiModel)
}