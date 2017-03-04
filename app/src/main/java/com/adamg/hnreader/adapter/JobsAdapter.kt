package com.adamg.hnreader.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamg.hnreader.AppConstants
import com.adamg.hnreader.R
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.models.Job
import com.adamg.hnreader.views.askview.JobActivity
import kotlinx.android.synthetic.main.job_card.view.*

class JobsAdapter(var context: Context, var stories: List<Item>): RecyclerView.Adapter<JobsAdapter.JobsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): JobsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.job_card, parent, false)
        return JobsViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        val story = stories[position]
        holder.bindStory(story)
    }

    override fun getItemCount() = stories.size

    inner class JobsViewHolder(val view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        fun bindStory(story: Item){
            view.storyNumber.text = (adapterPosition+1).toString() + "."
            view.storyTitle.text = story.title
            view.storyTime.text = story.timeAgo
            view.storyDomain.text = story.domain
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val story = stories[adapterPosition]
            var intent = Intent(context, JobActivity::class.java)
            intent.putExtra(AppConstants.ITEM, Job.fromItem(story))
            context.startActivity(intent)
        }
    }

}
