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
import com.adamg.hnreader.models.Type
import com.adamg.hnreader.views.storyview.StoryActivity
import kotlinx.android.synthetic.main.story_card.view.*

class NewStoriesAdapter(var context: Context, var stories: List<Item>): RecyclerView.Adapter<NewStoriesAdapter.NewItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewItemsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.story_card, parent, false)
        return NewItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewItemsViewHolder, position: Int) {
        val story = stories[position]
        holder.bindStory(story)
    }

    override fun getItemCount() = stories.size

    inner class NewItemsViewHolder(val view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        fun bindStory(story: Item){
            view.storyNumber.text = (adapterPosition+1).toString() + "."
            view.storyTitle.text = story.title
            view.storyBy.text = story.user
            view.storyPoints.text = story.points.toString()
            view.storyTime.text = story.timeAgo
            view.commentsCount.text = story.commentsCount.toString()
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val story = stories[adapterPosition]
            if (story.type == Type.LINK) {
                var intent = Intent(context, StoryActivity::class.java)
                intent.putExtra(AppConstants.ITEM, story)
                context.startActivity(intent)
            }
        }
    }

}
