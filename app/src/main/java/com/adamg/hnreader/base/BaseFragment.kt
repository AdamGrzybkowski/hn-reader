package com.adamg.hnreader.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.evernote.android.state.StateSaver

abstract class BaseFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StateSaver.restoreInstanceState(this, savedInstanceState)
        injectDependencies()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let {
            StateSaver.saveInstanceState(this, it)
        }
    }

    abstract fun injectDependencies()
}