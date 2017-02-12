package com.adamg.hnreader.views.newstories

import com.adamg.hnreader.api.HackerNewsApi
import com.adamg.hnreader.models.Story
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class NewStoriesPresenter @Inject constructor(private val hackerNewsApi: HackerNewsApi) : MvpBasePresenter<NewStoriesView>() {

    private var compositeSubscription: CompositeSubscription? = null

    fun loadNewStories(pullToRefresh: Boolean){
        view?.render(NewStoriesModel.Loading())
        var subscribtion = hackerNewsApi.getNewStories(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { stories: List<Story> ->
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