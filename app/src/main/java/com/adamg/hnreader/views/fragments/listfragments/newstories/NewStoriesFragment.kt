package com.adamg.hnreader.views.fragments.listfragments.newstories

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
import com.adamg.hnreader.views.fragments.listfragments.ItemsAdapter
import com.adamg.hnreader.dagger.component.DaggerNewStoriesComponent
import com.adamg.hnreader.dagger.component.NewStoriesComponent
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.views.activities.itemdetailactivity.ItemDetailsActivity
import com.adamg.hnreader.views.base.BaseFragmentMvp
import com.adamg.hnreader.views.fragments.listfragments.ItemListener
import com.adamg.hnreader.views.fragments.listfragments.ItemsUiModel
import com.adamg.hnreader.views.fragments.listfragments.ItemsView
import kotlinx.android.synthetic.main.fragment_new_stories.*
import org.jetbrains.anko.support.v4.startActivity

class NewStoriesFragment : BaseFragmentMvp<ItemsView, NewStoriesPresenter>(), ItemsView,
        SwipeRefreshLayout.OnRefreshListener, ItemListener {

    lateinit private var newStoriesComponent: NewStoriesComponent
    lateinit private var adapter: ItemsAdapter

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
        loadData(false)
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

    override fun render(itemsUiModel: ItemsUiModel){
        when {
            itemsUiModel.isEmpty() -> showEmptyResultState()
            itemsUiModel.isError() -> showErrorState(itemsUiModel.getError())
            itemsUiModel.isLoading() -> showLoadingState()
            itemsUiModel.isSuccess() -> showResultState(itemsUiModel.getItems())
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
        ItemDetailsActivity.start(activity, itemId = item.id)
    }

    override fun injectDependencies(){
        newStoriesComponent = DaggerNewStoriesComponent.builder()
                .applicationComponent(HNApp.applicationComponent)
                .build()
    }
}
