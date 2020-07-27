package com.github.vertex13.gameslist.presentation.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnEndScrollListener(private val callback: () -> Unit) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val llm = recyclerView.layoutManager as LinearLayoutManager
        if (llm.findLastVisibleItemPosition() >= llm.itemCount - 1) {
            callback()
        }
    }
}
