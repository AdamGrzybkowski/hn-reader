package com.adamg.hnreader.views.newstories

import com.adamg.hnreader.networking.HackerNewsApi
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class NewStoriesPresenter : MvpBasePresenter<NewStoriesView>() {

    private val hackerNewsApi: HackerNewsApi = HackerNewsApi.create()
    private var compositeSubscription: CompositeSubscription? = null

    fun loadNewStories(pullToRefresh: Boolean){
        view?.showLoading(true)
        var subscribtion = hackerNewsApi.getNewStories(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { stories ->
                            view?.setData(stories)
                        },
                        { error ->
                            view?.showError(error, pullToRefresh)}
                        )

        compositeSubscription?.add(subscribtion)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        compositeSubscription?.unsubscribe()
    }

    override fun attachView(view: NewStoriesView?) {
        super.attachView(view)
        compositeSubscription = CompositeSubscription()

    }
}