package com.adamg.hnreader.views.newstories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.adamg.hnreader.R
import com.adamg.hnreader.models.Story

class NewStoriesAdapter(var stories: List<Story>): RecyclerView.Adapter<NewStoriesAdapter.NewItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewItemsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_card, parent, false)
        return NewItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewItemsViewHolder, position: Int) {
        val story = stories[position]
        holder.storyNumber.text = (position+1).toString() + "."
        holder.storyTitle.text = story.title
        holder.storyBy.text = story.user
        holder.storyPoints.text = story.points.toString()
        holder.storyTime.text = story.timeAgo
        holder.commentsCount.text = story.commentsCount.toString()
    }

    override fun getItemCount() = stories.size

    class NewItemsViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        @BindView(R.id.storyTittle)
        lateinit var storyTitle: TextView

        @BindView(R.id.storyBy)
        lateinit var storyBy: TextView

        @BindView(R.id.storyNumber)
        lateinit var storyNumber: TextView

        @BindView(R.id.storyPoints)
        lateinit var storyPoints: TextView

        @BindView(R.id.storyTime)
        lateinit var storyTime: TextView

        @BindView(R.id.commentsCount)
        lateinit var commentsCount: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }

}
