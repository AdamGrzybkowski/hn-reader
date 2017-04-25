package com.adamg.hnreader.views.askview

import com.adamg.hnreader.api.HackerNewsApi
import com.adamg.hnreader.base.BasePresenter
import com.adamg.hnreader.models.Ask
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class AskPresenter @Inject constructor(val hackerNewsApi: HackerNewsApi): BasePresenter<AskView>() {

    fun loadAsk(askId: Long){
        view?.render(AskModel.Loading())
        hackerNewsApi.getAsk(askId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { ask: Ask -> view?.render(AskModel.Result(ask))},
                        { error: Throwable -> view?.render(AskModel.Error(error.message))}
                )
    }

}