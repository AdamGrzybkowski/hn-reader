package com.adamg.hnreader.views.comments

import com.adamg.hnreader.api.HackerNewsApi
import com.adamg.hnreader.base.BasePresenter
import com.adamg.hnreader.models.Comment
import com.adamg.hnreader.models.Item
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class CommentsPresenter @Inject constructor(val hackerNewsApi: HackerNewsApi): BasePresenter<CommentsView>() {

    fun loadComments(itemId: Long, pullToRefresh: Boolean){
        view?.render(CommentsModel.Loading())
        val subscription = hackerNewsApi.getItem(itemId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        { item: Item ->
                            if (item.comments.isEmpty()) {
                                view?.render(CommentsModel.EmptyResult())
                            } else {
                                view?.render(CommentsModel.Result(item.comments.map { comment -> CommentCardModel(comment) }))
                            }
                        },
                        { error: Throwable -> error.message?.let{
                            view?.render(CommentsModel.Error(it)) }
                        }
                )
        compositeSubscription?.add(subscription)
    }
}