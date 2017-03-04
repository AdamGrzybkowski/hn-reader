package com.adamg.hnreader.views.listfragments.newstories

import com.adamg.hnreader.api.HackerNewsApi
import com.adamg.hnreader.base.BasePresenter
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.views.listfragments.ItemsModel
import com.adamg.hnreader.views.listfragments.ItemsView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class NewStoriesPresenter @Inject constructor(private val hackerNewsApi: HackerNewsApi) : BasePresenter<ItemsView>() {

    fun loadNewStories(pullToRefresh: Boolean){
        view?.render(ItemsModel.Loading())
        val subscription = hackerNewsApi.getNewStories(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { stories: List<Item> ->
                            if (stories.isEmpty()) {
                                view?.render(ItemsModel.EmptyResult())
                            } else {
                                view?.render(ItemsModel.Result(stories))
                            }
                        },
                        { error: Throwable -> error.message?.let{
                            view?.render(ItemsModel.Error(it)) }
                        }
                )

        compositeSubscription?.add(subscription)
    }

}