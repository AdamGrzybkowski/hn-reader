package com.adamg.hnreader.views.fragments.listfragments.newstories

import com.adamg.hnreader.networking.HackerNewsApi
import com.adamg.hnreader.views.base.BasePresenter
import com.adamg.hnreader.views.fragments.listfragments.ItemsUiModel
import com.adamg.hnreader.views.fragments.listfragments.ItemsView
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.subscribeBy
import rx.schedulers.Schedulers
import javax.inject.Inject

class ShowsPresenter @Inject constructor(private val hackerNewsApi: HackerNewsApi) : BasePresenter<ItemsView>() {

    fun loadNewShows(pullToRefresh: Boolean){
        val subscription = hackerNewsApi.getShows(1)
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