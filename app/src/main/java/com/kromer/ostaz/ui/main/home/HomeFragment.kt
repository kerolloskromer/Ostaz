package com.kromer.ostaz.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kromer.ostaz.data.model.Post
import com.kromer.ostaz.databinding.FragmentHomeBinding
import com.kromer.ostaz.ui.base.BaseFragment
import com.kromer.ostaz.utils.MediaPlayerManager
import com.kromer.ostaz.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), ItemClickListener {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: PostsAdapter
    private var posts: ArrayList<Post> = ArrayList()
    private var currentItem: Post? = null
    private var currentPosition: Int = 0

    override fun getVBInflater(): (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PostsAdapter(posts, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        homeViewModel.posts.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        adapter.apply {
                            setData(resource.data!!)
                            notifyDataSetChanged()
                        }
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })

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
