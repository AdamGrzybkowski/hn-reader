package com.adamg.hnreader.views.listfragments.shows


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamg.hnreader.HNApp
import com.adamg.hnreader.R
import com.adamg.hnreader.adapter.ItemsAdapter
import com.adamg.hnreader.base.BaseFragmentMvp
import com.adamg.hnreader.dagger.component.DaggerShowsComponent
import com.adamg.hnreader.dagger.component.ShowsComponent
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.views.listfragments.ItemsModel
import com.adamg.hnreader.views.listfragments.ItemsView
import com.adamg.hnreader.views.listfragments.newstories.ShowsPresenter
import com.evernote.android.state.State
import kotlinx.android.synthetic.main.fragment_new_stories.*


/**
 * A simple [Fragment] subclass.
 */
class ShowsFragment : BaseFragmentMvp<ItemsView, ShowsPresenter>(), ItemsView, SwipeRefreshLayout.OnRefreshListener {

    lateinit private var showsComponent: ShowsComponent
    lateinit private var adapter: ItemsAdapter

    @State
    var state: ItemsModel = ItemsModel.Loading()

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
        adapter = ItemsAdapter(activity, listOf())
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        if (state is ItemsModel.Loading) {
            loadData(false)
        } else {
            render(state)
        }
    }

    override fun onRefresh() {
        loadData(true)
    }

    private fun loadData(pullToRefresh: Boolean){
        presenter.loadNewShows(pullToRefresh)
    }

    override fun createPresenter(): ShowsPresenter {
        return showsComponent.presenter()
    }

    override fun render(viewState: ItemsModel){
        state = viewState
        when(viewState){
            is ItemsModel.EmptyResult -> showEmptyResultState()
            is ItemsModel.Error -> showErrorState(viewState.error)
            is ItemsModel.Loading -> showLoadingState()
            is ItemsModel.Result -> showResultState(viewState.stories)
        }
    }

    private fun showEmptyResultState() {
        errorView.visibility = View.VISIBLE
        errorView.text = getString(R.string.no_results_message)
        contentView.isRefreshing = false
        recycleView.visibility = View.GONE
    }

    fun showLoadingState(){
        contentView.isRefreshing = true
        errorView.visibility = View.GONE
        recycleView.visibility = View.GONE
    }

    fun showErrorState(error: String){
        errorView.visibility = View.VISIBLE
        errorView.text = error
        contentView.isRefreshing = false
        recycleView.visibility = View.GONE
    }

    fun showResultState(stories: List<Item>){
        contentView.isRefreshing = false
        errorView.visibility = View.GONE
        recycleView.visibility = View.VISIBLE
        adapter.stories = stories
        adapter.notifyDataSetChanged()
    }

    override fun injectDependencies(){
        showsComponent = DaggerShowsComponent.builder()
                .applicationComponent(HNApp.applicationComponent)
                .build()
    }
}
