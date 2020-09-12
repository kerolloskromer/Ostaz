package com.kromer.ostaz.presentation.ui.main.home

import com.kromer.ostaz.domain.entity.Post


interface ItemStateListener {
    fun itemState(item: Post, position: Int, isPLaying: Boolean)
}