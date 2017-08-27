package com.adamg.hnreader.views.base

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

abstract class BaseFragmentMvp<V: MvpView, P: MvpPresenter<V>>: MvpFragment<V, P>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    abstract fun injectDependencies()
}