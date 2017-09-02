package com.adamg.hnreader.views.fragments.listfragments.shows


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamg.hnreader.HNApp
import com.adamg.hnreader.R
import com.adamg.hnreader.views.fragments.listfragments.ItemsAdapter
import com.adamg.hnreader.dagger.component.DaggerJobsComponent
import com.adamg.hnreader.dagger.component.JobsComponent
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.views.base.BaseFragmentMvp
import com.adamg.hnreader.views.fragments.listfragments.ItemListener
import com.adamg.hnreader.views.fragments.listfragments.ItemsUiModel
import com.adamg.hnreader.views.fragments.listfragments.ItemsView
import com.adamg.hnreader.views.fragments.listfragments.newstories.JobsPresenter
import kotlinx.android.synthetic.main.fragment_new_stories.*


/**
 * A simple [Fragment] subclass.
 */
class JobsFragment : BaseFragmentMvp<ItemsView, JobsPresenter>(), ItemsView,
        SwipeRefreshLayout.OnRefreshListener, ItemListener {

    lateinit private var jobsComponent: JobsComponent
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
        presenter.loadNewJobs(pullToRefresh)
    }

    override fun createPresenter(): JobsPresenter {
        return jobsComponent.presenter()
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
        errorView.visibility = View.VISIBLE
        errorView.text = getString(R.string.no_results_message)
        contentView.isRefreshing = false
        recycleView.visibility = View.GONE
    }

    fun showLoadingState(){
        contentView.isRefreshing = true
        errorView.visibility = View.GONE
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

    override fun onItemClicked(item: Item) {
//        val intent = Intent(activity, JobActivity::class.java)
//        intent.putExtra(AppConstants.ITEM, Job.fromItem(item))
//        startActivity(intent)
    }

    override fun injectDependencies(){
        jobsComponent = DaggerJobsComponent.builder()
                .applicationComponent(HNApp.applicationComponent)
                .build()
    }
}
