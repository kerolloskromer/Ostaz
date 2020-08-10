package com.kromer.ostaz.ui.main.home

import com.kromer.ostaz.data.model.Post


interface ItemStateListener {
    fun itemState(item: Post, position: Int, isPLaying: Boolean)
}