package com.adamg.hnreader.views.newstories

import com.adamg.hnreader.api.HackerNewsApi
import com.adamg.hnreader.base.BasePresenter
import com.adamg.hnreader.models.Item
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class NewStoriesPresenter @Inject constructor(private val hackerNewsApi: HackerNewsApi) : BasePresenter<NewStoriesView>() {

    fun loadNewStories(pullToRefresh: Boolean){
        view?.render(NewStoriesModel.Loading())
        val subscription = hackerNewsApi.getNewStories(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { stories: List<Item> ->
                            if (stories.isEmpty()) {
                                view?.render(NewStoriesModel.EmptyResult())
                            } else {
                                view?.render(NewStoriesModel.Result(stories))
                            }
                        },
                        { error: Throwable -> error.message?.let{
                            view?.render(NewStoriesModel.Error(it)) }
                        }
                )

        compositeSubscription?.add(subscription)
    }

}