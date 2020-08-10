package com.kromer.ostaz.ui.main.notifications

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
import com.kromer.ostaz.data.model.NotificationObj
import com.kromer.ostaz.ui.base.BaseFragment
import com.kromer.ostaz.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : BaseFragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var adapter: NotificationsAdapter
    private var notifications: ArrayList<NotificationObj> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerView)
        val progressBar: ProgressBar = root.findViewById(R.id.progressBar)
        adapter = NotificationsAdapter(notifications)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        notificationsViewModel.notifications.observe(viewLifecycleOwner, Observer {
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
        notificationsViewModel.register(preferences.firebaseToken)
        return root
    }
}
