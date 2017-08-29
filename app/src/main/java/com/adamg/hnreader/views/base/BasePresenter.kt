package com.adamg.hnreader.views.base

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import rx.subscriptions.CompositeSubscription

abstract class BasePresenter<V: MvpView>: MvpBasePresenter<V>() {

    protected var compositeSubscription: CompositeSubscription? = null

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        compositeSubscription?.unsubscribe()
    }

    override fun attachView(view: V) {
        super.attachView(view)
        compositeSubscription = CompositeSubscription()
    }
}