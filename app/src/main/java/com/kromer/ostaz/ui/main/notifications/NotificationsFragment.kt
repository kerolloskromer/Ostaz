package com.kromer.ostaz.ui.main.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kromer.ostaz.data.local.prefs.Preferences
import com.kromer.ostaz.data.model.NotificationObj
import com.kromer.ostaz.databinding.FragmentNotificationsBinding
import com.kromer.ostaz.ui.base.BaseFragment
import com.kromer.ostaz.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>() {

    private val notificationsViewModel: NotificationsViewModel by viewModels()

    private lateinit var adapter: NotificationsAdapter
    private var notifications: ArrayList<NotificationObj> = ArrayList()

    @Inject
    lateinit var preferences: Preferences

    override fun getVBInflater(): (LayoutInflater) -> FragmentNotificationsBinding =
        FragmentNotificationsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NotificationsAdapter(notifications)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        notificationsViewModel.notifications.observe(viewLifecycleOwner, Observer {
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
        notificationsViewModel.register(preferences.firebaseToken)
    }

}
