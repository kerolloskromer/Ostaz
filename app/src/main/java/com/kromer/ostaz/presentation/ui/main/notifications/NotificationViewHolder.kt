package com.kromer.ostaz.presentation.ui.main.notifications

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kromer.ostaz.data.model.NotificationObj
import kotlinx.android.synthetic.main.item_post.view.*

class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(notificationObj: NotificationObj) {
        itemView.text.text = notificationObj.text
    }
}