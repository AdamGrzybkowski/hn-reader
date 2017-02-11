package com.adamg.hnreader.views.newstories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamg.hnreader.R
import com.adamg.hnreader.models.Story
import kotlinx.android.synthetic.main.item_card.view.*

class NewStoriesAdapter(var stories: List<Story>): RecyclerView.Adapter<NewStoriesAdapter.NewItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewItemsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_card, parent, false)
        return NewItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewItemsViewHolder, position: Int) {
        val story = stories[position]
        holder.bindStory(story)
    }

    override fun getItemCount() = stories.size

    class NewItemsViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bindStory(story: Story){
            view.storyNumber.text = (adapterPosition+1).toString() + "."
            view.storyTitle.text = story.title
            view.storyBy.text = story.user
            view.storyPoints.text = story.points.toString()
            view.storyTime.text = story.timeAgo
            view.commentsCount.text = story.commentsCount.toString()
        }
    }

}
