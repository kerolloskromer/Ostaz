package com.kromer.ostaz.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kromer.ostaz.R
import com.kromer.ostaz.data.model.Post
import com.kromer.ostaz.ui.base.BaseFragment
import com.kromer.ostaz.utils.MediaPlayerManager
import com.kromer.ostaz.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(), ItemClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: PostsAdapter
    private var posts: ArrayList<Post> = ArrayList()
    private var currentItem: Post? = null
    private var currentPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerView)
        val progressBar: ProgressBar = root.findViewById(R.id.progressBar)
        adapter = PostsAdapter(posts, this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        homeViewModel.posts.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        adapter.apply {
                            setData(resource.data!!)
                            notifyDataSetChanged()
                        }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
        return root
    }

    override fun onClick(item: Post, position: Int, itemStateListener: ItemStateListener) {
        if (currentItem != null) {
            MediaPlayerManager.stop()
            itemStateListener.itemState(currentItem!!, currentPosition, false)
            currentItem = null
        }
        if (item.filePath != null && getExtension(item.filePath).equals(".mp3")) {
            MediaPlayerManager.start(item.filePath)
            currentItem = item
            currentPosition = position
            itemStateListener.itemState(item, position, true)
        }
    }

    fun getExtension(uri: String): String? {
        val dot = uri.lastIndexOf(".")
        if (dot >= 0) {
            return uri.substring(dot)
        }
        return ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MediaPlayerManager.stop()
    }
}
