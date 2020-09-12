package com.kromer.ostaz.presentation.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.kromer.ostaz.R
import com.kromer.ostaz.domain.entity.Post

class PostsAdapter(
    private var posts: ArrayList<Post>,
    private var itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflatedView = parent.inflate(R.layout.item_post, false)
        return PostViewHolder(inflatedView)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position], itemClickListener, this)
    }

    fun setData(posts: List<Post>) {
        this.posts.apply {
            clear()
            addAll(posts)
        }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}