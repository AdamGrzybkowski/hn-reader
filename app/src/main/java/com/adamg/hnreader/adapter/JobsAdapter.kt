package com.adamg.hnreader.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamg.hnreader.R
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.views.listfragments.ItemListener
import kotlinx.android.synthetic.main.job_card.view.*

class JobsAdapter(var stories: List<Item>, val itemListener: ItemListener): RecyclerView.Adapter<JobsAdapter.JobsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): JobsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.job_card, parent, false)
        return JobsViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        val story = stories[position]
        holder.bindStory(story)
    }

    override fun getItemCount() = stories.size

    inner class JobsViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bindStory(item: Item){
            view.storyTitle.text = item.title
            view.storyTime.text = item.time_ago
            view.storyDomain.text = item.domain
            view.setOnClickListener { itemListener.onItemClicked(item) }
        }
    }
}
