package com.adamg.hnreader.adapter

import android.opengl.Visibility
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamg.hnreader.R
import com.adamg.hnreader.models.Item
import com.adamg.hnreader.models.ItemType
import com.adamg.hnreader.views.listfragments.ItemListener
import kotlinx.android.synthetic.main.item_card.view.*

class ItemsAdapter(var stories: List<Item>, val itemListener: ItemListener): RecyclerView.Adapter<ItemsAdapter.NewItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewItemsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_card, parent, false)
        return NewItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewItemsViewHolder, position: Int) {
        val story = stories[position]
        holder.bindStory(story)
    }

    override fun getItemCount() = stories.size

    inner class NewItemsViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bindStory(item: Item){
            view.itemTitle.text = item.title
            item.user?.let { view.itemUser.text = view.context.getString(R.string.value_dot_separator, it) }
            val points = item.points
            view.itemPointsCount.text = points?.let { view.context.resources.getQuantityString(R.plurals.points, it, points) }
            view.itemTimeAgo.text = item.time_ago
            view.itemCommentsCount.text = view.context.resources.getQuantityString(R.plurals.comments, item.comments_count, item.comments_count)
            view.itemDomain.text = item.domain
            if (item.type == ItemType.JOB.value) {
                view.itemPointsCount.visibility = View.GONE
                view.itemCommentsCount.visibility = View.GONE
            } else {
                view.itemPointsCount.visibility = View.VISIBLE
                view.itemCommentsCount.visibility = View.VISIBLE
            }
            view.setOnClickListener{ itemListener.onItemClicked(item) }
        }
    }
}
