package com.adamg.hnreader.views.newstories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.adamg.hnreader.R

class NewStoriesAdapter(var items: List<Int>): RecyclerView.Adapter<NewStoriesAdapter.NewItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewItemsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_card, parent, false)
        return NewItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewItemsViewHolder, position: Int) {
        holder.textView.text = items[position].toString()
    }

    override fun getItemCount() = items.size

    class NewItemsViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        @BindView(R.id.text_view)
        lateinit var textView: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }

}
