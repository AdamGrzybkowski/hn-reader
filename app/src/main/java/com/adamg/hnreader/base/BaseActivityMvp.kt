package com.adamg.hnreader.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import com.evernote.android.state.StateSaver
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.MvpView

abstract class BaseActivityMvp<V: MvpView, P: MvpPresenter<V>>: MvpActivity<V, P>() {

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}