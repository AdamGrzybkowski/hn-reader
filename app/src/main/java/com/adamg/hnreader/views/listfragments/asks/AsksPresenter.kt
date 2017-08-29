package com.adamg.hnreader.views.listfragments.newstories

import com.adamg.hnreader.api.HackerNewsApi
import com.adamg.hnreader.views.base.BasePresenter
import com.adamg.hnreader.views.listfragments.ItemsUiModel
import com.adamg.hnreader.views.listfragments.ItemsView
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.subscribeBy
import rx.schedulers.Schedulers
import javax.inject.Inject

class AsksPresenter @Inject constructor(private val hackerNewsApi: HackerNewsApi) : BasePresenter<ItemsView>() {

    fun loadNewSAsks(pullToRefresh: Boolean){
        val subscription = hackerNewsApi.getAsks(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { ItemsUiModel.success(it) }
                .onErrorReturn { ItemsUiModel.error(it.message) }
                .startWith(ItemsUiModel.loading())
                .subscribeBy(
                        onNext = { view?.render(it)}
                )
        compositeSubscription?.add(subscription)
    }

}