package com.adamg.hnreader.views.askview

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.adamg.hnreader.AppConstants
import com.adamg.hnreader.HNApp
import com.adamg.hnreader.R
import com.adamg.hnreader.base.BaseActivity
import com.adamg.hnreader.base.BaseActivityMvp
import com.adamg.hnreader.dagger.component.AskComponent
import com.adamg.hnreader.dagger.component.AsksComponent
import com.adamg.hnreader.dagger.component.DaggerAskComponent
import com.adamg.hnreader.models.Ask
import com.adamg.hnreader.views.comments.CommentsFragment
import com.adamg.hnreader.views.listfragments.ItemsModel
import com.evernote.android.state.State
import kotlinx.android.synthetic.main.activity_story.*
import kotlinx.android.synthetic.main.ask_header.*

class AskActivity : BaseActivityMvp<AskView, AskPresenter>(), AskView {

    lateinit var askComponent: AskComponent
    @State
    var askModel: AskModel = AskModel.Loading()

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val ask = intent.extras.getParcelable<Ask>(AppConstants.ITEM)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, CommentsFragment.create(ask.id))
                .commit()

        render(askModel)
        presenter.loadAsk(ask.id)
    }

    override fun render(askModel: AskModel){
        this.askModel = askModel
        when(askModel){
            is AskModel.Error -> showErrorState(askModel.error)
            is AskModel.Loading -> showLoadingState()
            is AskModel.Result -> showResultState(askModel.ask)
        }
    }

    private fun showResultState(ask: Ask) {
        askTitle.text = ask.title
        askUser.text = ask.user
        if (ask.content != null) {
            askContent.text = ask.content
            askContent.visibility = VISIBLE
        } else {
            frameLayoutAskContent.visibility = GONE
        }
        askPoints.text = ask.points.toString()
        askProgressBar.visibility = GONE
    }

    private fun showLoadingState() {
        askContent.visibility = GONE
        askProgressBar.visibility = VISIBLE
    }

    private fun showErrorState(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun injectDependencies() {
        askComponent = DaggerAskComponent.builder()
                .applicationComponent(HNApp.applicationComponent)
                .build()
    }

    override fun createPresenter(): AskPresenter {
        return askComponent.presenter()
    }

}
