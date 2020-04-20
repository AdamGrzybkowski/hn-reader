package com.adamg.hnreader.views.activities.itemdetailactivity

import com.adamg.hnreader.models.Item
import com.hannesdorfmann.mosby3.mvp.MvpView

interface ItemDetailsView: MvpView {
    fun showItemDetails(item: Item)

}