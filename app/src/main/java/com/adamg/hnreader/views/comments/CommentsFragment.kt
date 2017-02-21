package com.adamg.hnreader.views.comments


import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamg.hnreader.AppConstants
import com.adamg.hnreader.HNApp
import com.adamg.hnreader.R
import com.adamg.hnreader.adapter.CommentsAdapter
import com.adamg.hnreader.base.BaseFragmentMvp
import com.adamg.hnreader.dagger.component.CommentsComponent
import com.adamg.hnreader.dagger.component.DaggerCommentsComponent
import com.adamg.hnreader.models.Comment
import com.evernote.android.state.State
import kotlinx.android.synthetic.main.fragment_comments.*

class CommentsFragment:  BaseFragmentMvp<CommentsView, CommentsPresenter>(), CommentsView, SwipeRefreshLayout.OnRefreshListener {

    lateinit private var commentsComponent: CommentsComponent
    lateinit private var adapter: CommentsAdapter

    @State
    var state: CommentsModel = CommentsModel.Loading()
    @State
    var itemId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        itemId = arguments.getLong(AppConstants.ITEM_ID)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentView.setOnRefreshListener(this)
        adapter = CommentsAdapter(activity, listOf())
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        if (state is CommentsModel.Loading) {
            loadData(false)
        } else {
            render(state)
        }
    }

    override fun onRefresh() {
        loadData(true)
    }

    private fun loadData(pullToRefresh: Boolean){
        presenter.loadComments(itemId, pullToRefresh)
    }

    override fun render(model: CommentsModel) {
        state = model
        when(model){
            is CommentsModel.EmptyResult -> showEmptyResultState()
            is CommentsModel.Error -> showErrorState(model.error)
            is CommentsModel.Loading -> showLoadingState()
            is CommentsModel.Result -> showResultState(model.comments)
        }
    }

    private fun showErrorState(error: String) {
        errorView.visibility = View.VISIBLE
        errorView.text = error
        contentView.isRefreshing = false
        recycleView.visibility = View.GONE
    }

    private fun showResultState(comments: List<Comment>) {
        contentView.isRefreshing = false
        errorView.visibility = View.GONE
        recycleView.visibility = View.VISIBLE
        adapter.comments = comments
        adapter.notifyDataSetChanged()
    }

    private fun showLoadingState() {
        contentView.isRefreshing = true
        errorView.visibility = View.GONE
        recycleView.visibility = View.GONE
    }

    private fun showEmptyResultState() {
        errorView.visibility = View.VISIBLE
        errorView.text = getString(R.string.no_comments_message)
        contentView.isRefreshing = false
        recycleView.visibility = View.GONE
    }

    override fun createPresenter() = commentsComponent.presenter()

    override fun injectDependencies() {
        commentsComponent = DaggerCommentsComponent.builder()
                .applicationComponent(HNApp.applicationComponent)
                .build()
    }

    companion object Factory {
        fun create(itemId: Long): CommentsFragment{
            var commentsFragment = CommentsFragment()
            var bundle = Bundle()
            bundle.putLong(AppConstants.ITEM_ID, itemId)
            commentsFragment.arguments = bundle
            return commentsFragment
        }
    }

}
