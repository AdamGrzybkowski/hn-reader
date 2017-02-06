package com.adamg.hnreader.views.newstories


import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.adamg.hnreader.R
import com.adamg.hnreader.models.Story
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState

class NewStoriesFragment : MvpLceViewStateFragment<SwipeRefreshLayout, List<Story>, NewStoriesView, NewStoriesPresenter>(), NewStoriesView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycleView)
    lateinit var recycleView: RecyclerView

    @BindView(R.id.contentView)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    var adapter: NewStoriesAdapter? = null
    var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_new_posts, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener(this)
        adapter = NewStoriesAdapter(listOf())
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        loadData(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    override fun onRefresh() {
        loadData(true)
    }

    override fun createViewState(): LceViewState<List<Story>, NewStoriesView> {
        return RetainingLceViewState()
    }

    override fun createPresenter(): NewStoriesPresenter {
        return NewStoriesPresenter()
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.loadNewStories(pullToRefresh)
    }

    override fun showError(e: Throwable?, pullToRefresh: Boolean) {
        super.showError(e, pullToRefresh)
        swipeRefreshLayout.isRefreshing = pullToRefresh
    }

    override fun setData(stories: List<Story>?) {
        stories?.let {
            adapter?.stories = stories
            adapter?.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun showLoading(pullToRefresh: Boolean) {
        super.showLoading(pullToRefresh)
        swipeRefreshLayout.isRefreshing = pullToRefresh
    }

    override fun getData(): List<Story>? {
        return adapter?.stories
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String? {
        return e?.message
    }

    override fun showContent() {
        super.showContent()
    }
}
