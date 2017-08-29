package com.adamg.hnreader.views.base

import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    abstract fun injectDependencies()
}