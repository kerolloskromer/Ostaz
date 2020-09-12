package com.kromer.ostaz.presentation.ui.main.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kromer.ostaz.domain.entity.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(post: Post, itemClickListener: ItemClickListener, adapter: PostsAdapter) {
        if (post.isPLaying) {
            // TODO change UI accordingly
        } else {
            // TODO change UI accordingly
        }

        itemView.text.text = post.text

        val itemStateListener: ItemStateListener = object : ItemStateListener {
            override fun itemState(item: Post, position: Int, isPLaying: Boolean) {
                item.isPLaying = isPLaying
                adapter.notifyItemChanged(position)
            }
        }

        itemView.setOnClickListener {
            itemClickListener.onClick(post, adapterPosition, itemStateListener)
        }
    }
}