package com.adamg.hnreader.base

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby.mvp.MvpView
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