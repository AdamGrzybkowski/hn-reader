package com.adamg.hnreader.adapter

import android.app.Activity
import android.graphics.Point
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamg.hnreader.R
import com.adamg.hnreader.models.Comment
import kotlinx.android.synthetic.main.comment_card.view.*


class CommentsAdapter(var context: Activity, var comments: List<Comment>): RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

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
            if (comment.level != 0) {
                val layoutParams = view.cardView.layoutParams
                layoutParams.width = calculateWidth(comment.level)
                view.cardView.layoutParams = layoutParams
            }
        }

        private fun calculateWidth(level: Int): Int {
            val display = context.windowManager.defaultDisplay
            var point = Point()
            display.getSize(point)
            val width = point.x

            return width - (width/20) * level - 8
        }
    }
}