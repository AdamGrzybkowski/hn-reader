package com.adamg.hnreader.views.activities.itemdetailactivity

import android.util.Log
import com.adamg.hnreader.data.repository.ItemRepository
import com.adamg.hnreader.views.base.BasePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.subscribeBy
import javax.inject.Inject

class ItemDetailsPresenter @Inject constructor(private val itemRepository: ItemRepository): BasePresenter<ItemDetailsView>() {

    fun loadItem(itemId: Long) {
        itemRepository.getItem(itemId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { view?.showItemDetails(it!!)},
                        onError = { Log.e(ItemDetailsPresenter::class.java.simpleName, "LOL WTF", it)}
                )
    }

}