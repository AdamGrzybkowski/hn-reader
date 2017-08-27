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
import com.adamg.hnreader.dagger.component.CommentsComponent
import com.adamg.hnreader.dagger.component.DaggerCommentsComponent
import com.adamg.hnreader.views.base.BaseFragmentMvp
import kotlinx.android.synthetic.main.fragment_comments.*

class CommentsFragment:  BaseFragmentMvp<CommentsView, CommentsPresenter>(), CommentsView,
        SwipeRefreshLayout.OnRefreshListener, CommentsAdapter.CommentsListener {

    lateinit private var commentsComponent: CommentsComponent
    lateinit private var adapter: CommentsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentView.setOnRefreshListener(this)
        adapter = CommentsAdapter(mutableListOf(), this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        loadData(false)
    }

    override fun onRefresh() {
        loadData(true)
    }

    private fun loadData(pullToRefresh: Boolean){
        presenter.loadComments(arguments.getLong(AppConstants.ITEM_ID), pullToRefresh)
    }

    override fun render(model: CommentsModel) {
        when(model){
            is CommentsModel.EmptyResult -> showEmptyResultState()
            is CommentsModel.Error -> showErrorState(model.error)
            is CommentsModel.Loading -> showLoadingState()
            is CommentsModel.Result -> showResultState(model.commentCardModels)
        }
    }

    private fun showErrorState(error: String) {
        errorView.visibility = View.VISIBLE
        errorView.text = error
        contentView.isRefreshing = false
        recycleView.visibility = View.GONE
    }

    private fun showResultState(commentCardModel: List<CommentCardModel>) {
        contentView.isRefreshing = false
        errorView.visibility = View.GONE
        recycleView.visibility = View.VISIBLE
        val mutableComments = mutableListOf<CommentCardModel>()
        mutableComments.addAll(commentCardModel)
        adapter.commentCardModels = mutableComments
        adapter.notifyDataSetChanged()
    }

    private fun showLoadingState() {
        contentView.isRefreshing = true
        errorView.visibility = View.GONE
    }

    private fun showEmptyResultState() {
        errorView.visibility = View.VISIBLE
        errorView.text = getString(R.string.no_comments_message)
        contentView.isRefreshing = false
        recycleView.visibility = View.GONE
    }

    override fun onCommentsStateChanged(commentCardModels: MutableList<CommentCardModel>) {
//        state = CommentsModel.Result(commentCardModels)
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
