package com.adamg.hnreader.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamg.hnreader.R
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.views.listfragments.ItemListener
import kotlinx.android.synthetic.main.story_card.view.*

class ItemsAdapter(var stories: List<Item>, val itemListener: ItemListener): RecyclerView.Adapter<ItemsAdapter.NewItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewItemsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.story_card, parent, false)
        return NewItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewItemsViewHolder, position: Int) {
        val story = stories[position]
        holder.bindStory(story)
    }

    override fun getItemCount() = stories.size

    inner class NewItemsViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bindStory(item: Item){
            view.storyTitle.text = item.title
            view.storyBy.text = item.user
            view.storyPoints.text = item.points.toString()
            view.storyTime.text = item.timeAgo
            view.commentsCount.text = item.commentsCount.toString()
            view.setOnClickListener{ itemListener.onItemClicked(item) }
        }
    }
}
