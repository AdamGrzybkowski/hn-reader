package com.adamg.hnreader.views.newstories

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.adamg.hnreader.HNApp
import com.adamg.hnreader.R
import com.adamg.hnreader.adapter.NewStoriesAdapter
import com.adamg.hnreader.base.BaseFragmentMvp
import com.adamg.hnreader.dagger.component.DaggerNewStoriesComponent
import com.adamg.hnreader.dagger.component.NewStoriesComponent
import com.adamg.hnreader.models.Story
import com.evernote.android.state.State
import kotlinx.android.synthetic.main.fragment_new_stories.*

class NewStoriesFragment : BaseFragmentMvp<NewStoriesView, NewStoriesPresenter>(), NewStoriesView, SwipeRefreshLayout.OnRefreshListener {

    lateinit private var newStoriesComponent: NewStoriesComponent
    lateinit private var adapter: NewStoriesAdapter

    @State
    var state: NewStoriesModel = NewStoriesModel.Loading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.fragment_new_stories, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentView.setOnRefreshListener(this)
        adapter = NewStoriesAdapter(activity, listOf())
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        if (state is NewStoriesModel.Loading) {
            loadData(false)
        } else {
            render(state)
        }
    }
    override fun onRefresh() {
        loadData(true)
    }

    private fun loadData(pullToRefresh: Boolean){
        presenter.loadNewStories(pullToRefresh)
    }

    override fun createPresenter(): NewStoriesPresenter {
        return newStoriesComponent.presenter()
    }

    override fun render(viewState: NewStoriesModel){
        state = viewState
        when(viewState){
            is NewStoriesModel.EmptyResult -> showEmptyResultState()
            is NewStoriesModel.Error -> showErrorState(viewState.error)
            is NewStoriesModel.Loading -> showLoadingState()
            is NewStoriesModel.Result -> showData(viewState.stories)
        }
    }

    private fun showEmptyResultState() {
        errorView.visibility = VISIBLE
        errorView.text = "No results"
        contentView.isRefreshing = false
        recycleView.visibility = GONE
    }

    fun showLoadingState(){
        contentView.isRefreshing = true
        errorView.visibility = GONE
        recycleView.visibility = GONE
    }

    fun showErrorState(error: String){
        errorView.visibility = VISIBLE
        errorView.text = error
        contentView.isRefreshing = false
        recycleView.visibility = GONE
    }

    fun showData(stories: List<Story>){
        contentView.isRefreshing = false
        errorView.visibility = GONE
        recycleView.visibility = VISIBLE
        adapter.stories = stories
        adapter.notifyDataSetChanged()
    }

    override fun injectDependencies(){
        newStoriesComponent = DaggerNewStoriesComponent.builder()
                .applicationComponent(HNApp.applicationComponent)
                .build()
    }
}
