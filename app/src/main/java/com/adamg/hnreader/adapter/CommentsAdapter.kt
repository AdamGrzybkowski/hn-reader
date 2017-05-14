package com.adamg.hnreader.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import com.adamg.hnreader.R
import com.adamg.hnreader.models.Comment
import com.adamg.hnreader.views.comments.CommentCardModel
import kotlinx.android.synthetic.main.comment_card.view.*


class CommentsAdapter(var context: Activity,
                      var commentCardModels: MutableList<CommentCardModel>,
                      var commentsListener: CommentsListener): RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    private val cardViewMaxWeight = 20

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentViewHolder{
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.comment_card, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindComment(commentCardModels[position])
    }

    override fun getItemCount() = commentCardModels.size


    inner class CommentViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bindComment(commentCardModel: CommentCardModel){
            view.commentAuthor.text = commentCardModel.comment.user
            view.commentAgo.text = commentCardModel.comment.time_ago
            view.commentContent.text = commentCardModel.comment.content
            if(commentCardModel.comment.comments.isNotEmpty()) {
                view.linearLayoutShowMoreComments.visibility = VISIBLE
                view.textViewAnswerCount.text = String.format("(%d)", commentCardModel.comment.comments.size)
                if(commentCardModel.isExpanded) {
                    view.textViewHideReplies.visibility = VISIBLE
                    view.textViewShowReplies.visibility = GONE
                } else {
                    view.textViewShowReplies.visibility = VISIBLE
                    view.textViewHideReplies.visibility = GONE
                }
            } else {
                view.linearLayoutShowMoreComments.visibility = GONE
            }
            val layoutParams = view.cardView.layoutParams as LinearLayout.LayoutParams
            layoutParams.weight = (cardViewMaxWeight - commentCardModel.comment.level).toFloat()
            view.cardView.layoutParams = layoutParams
            view.textViewShowReplies.setOnClickListener {
                commentCardModels.addAll(commentCardModels.indexOf(commentCardModel)+1, commentCardModel.comment.comments.map { comment -> CommentCardModel(comment) })
                commentCardModel.isExpanded = true
                notifyDataSetChanged()
                commentsListener.onCommentsStateChanged(commentCardModels)
            }
            view.textViewHideReplies.setOnClickListener {
                val allCommentsToDelete = getAllComments(commentCardModel.comment.comments)
                commentCardModels.removeAll(allCommentsToDelete.map { comment -> CommentCardModel(comment) })
                commentCardModel.isExpanded = false
                notifyDataSetChanged()
                commentsListener.onCommentsStateChanged(commentCardModels)
            }
        }


        private fun getAllComments(comments: List<Comment>): List<Comment>{
            var commentList: MutableList<Comment> = mutableListOf()
            comments.forEach {
                commentList.add(it)
                if (it.comments.isNotEmpty()) {
                    commentList.addAll(getAllComments(it.comments))
                }
            }
            return commentList
        }

    }

    interface CommentsListener {
        fun onCommentsStateChanged(commentCardModels: MutableList<CommentCardModel>)
    }
}