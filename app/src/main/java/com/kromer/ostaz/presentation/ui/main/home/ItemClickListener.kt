package com.kromer.ostaz.presentation.ui.main.home

import com.kromer.ostaz.domain.entity.Post


interface ItemClickListener {
    fun onClick(item: Post, position: Int, itemStateListener: ItemStateListener)
}