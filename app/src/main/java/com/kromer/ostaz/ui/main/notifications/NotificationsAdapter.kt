package com.kromer.ostaz.ui.main.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.kromer.ostaz.R
import com.kromer.ostaz.data.model.NotificationObj

class NotificationsAdapter(private var notifications: ArrayList<NotificationObj>) :
    RecyclerView.Adapter<NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val inflatedView = parent.inflate(R.layout.item_notification, false)
        return NotificationViewHolder(inflatedView)
    }

    override fun getItemCount() = notifications.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    fun setData(notifications: List<NotificationObj>) {
        this.notifications.apply {
            clear()
            addAll(notifications)
        }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}