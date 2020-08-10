package com.kromer.ostaz.ui.main.home

import com.kromer.ostaz.data.model.Post


interface ItemClickListener {
    fun onClick(item: Post, position: Int, itemStateListener: ItemStateListener)
}