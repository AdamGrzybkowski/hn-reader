package com.adamg.hnreader.views.askview

import android.os.Bundle
import com.adamg.hnreader.AppConstants
import com.adamg.hnreader.R
import com.adamg.hnreader.base.BaseActivity
import com.adamg.hnreader.models.Ask
import com.adamg.hnreader.views.comments.CommentsFragment
import com.evernote.android.state.State
import kotlinx.android.synthetic.main.activity_story.*
import kotlinx.android.synthetic.main.ask_header.*

class AskActivity : BaseActivity() {

    @State
    lateinit var ask: Ask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ask = intent.extras.getParcelable<Ask>(AppConstants.ITEM)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, CommentsFragment.create(ask.id))
                .commit()

        showStory(ask)
    }

    private fun showStory(ask: Ask) {
        askTitle.text = ask.title
        askUser.text = ask.user
        askContent.text = ask.content
        askPoints.text = ask.points.toString()
    }

    override fun injectDependencies() {}

}
