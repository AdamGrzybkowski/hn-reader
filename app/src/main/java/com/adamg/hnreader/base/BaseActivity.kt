package com.adamg.hnreader.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.evernote.android.state.StateSaver

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
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