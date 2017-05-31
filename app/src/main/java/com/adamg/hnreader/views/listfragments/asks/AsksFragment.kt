package com.adamg.hnreader.views.listfragments.newstories

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
import com.adamg.hnreader.adapter.ItemsAdapter
import com.adamg.hnreader.base.BaseFragmentMvp
import com.adamg.hnreader.dagger.component.AsksComponent
import com.adamg.hnreader.dagger.component.DaggerAsksComponent
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.utils.parseItemTypeForIntent
import com.adamg.hnreader.views.listfragments.ItemListener
import com.adamg.hnreader.views.listfragments.ItemsModel
import com.adamg.hnreader.views.listfragments.ItemsView
import com.evernote.android.state.State
import kotlinx.android.synthetic.main.fragment_new_stories.*

class AsksFragment : BaseFragmentMvp<ItemsView, AsksPresenter>(), ItemsView,
        SwipeRefreshLayout.OnRefreshListener, ItemListener {

    lateinit private var asksComponent: AsksComponent
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
        adapter = ItemsAdapter(listOf(), this)
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
        presenter.loadNewSAsks(pullToRefresh)
    }

    override fun createPresenter(): AsksPresenter{
        return asksComponent.presenter()
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
        errorView.visibility = VISIBLE
        errorView.text = getString(R.string.no_results_message)
        contentView.isRefreshing = false
        recycleView.visibility = GONE
    }

    fun showLoadingState(){
        contentView.isRefreshing = true
        errorView.visibility = GONE
    }

    fun showErrorState(error: String){
        errorView.visibility = VISIBLE
        errorView.text = error
        contentView.isRefreshing = false
        recycleView.visibility = GONE
    }

    fun showResultState(stories: List<Item>){
        contentView.isRefreshing = false
        errorView.visibility = GONE
        recycleView.visibility = VISIBLE
        adapter.stories = stories
        adapter.notifyDataSetChanged()
    }

    override fun onItemClicked(item: Item) {
        val intent = parseItemTypeForIntent(context, item)
        startActivity(intent)
    }

    override fun injectDependencies(){
        asksComponent = DaggerAsksComponent.builder()
                .applicationComponent(HNApp.applicationComponent)
                .build()
    }
}
