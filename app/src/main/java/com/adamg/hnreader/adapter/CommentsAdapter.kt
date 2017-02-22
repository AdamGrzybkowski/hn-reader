package com.adamg.hnreader.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.adamg.hnreader.R
import com.adamg.hnreader.models.Comment
import kotlinx.android.synthetic.main.comment_card.view.*


class CommentsAdapter(var context: Activity, var comments: List<Comment>): RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    private val cardViewMaxWeight = 20

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentViewHolder{
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.comment_card, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindComment(comments[position])
    }

    override fun getItemCount() = comments.size


    inner class CommentViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bindComment(comment: Comment){
            view.commentAuthor.text = comment.user
            view.commentAgo.text = comment.time_ago
            view.commentContent.text = comment.content
            val layoutParams = view.cardView.layoutParams as LinearLayout.LayoutParams
            layoutParams.weight = (cardViewMaxWeight - comment.level).toFloat()
            view.cardView.layoutParams = layoutParams
        }
    }
}