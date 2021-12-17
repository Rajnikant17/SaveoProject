package com.example.saveo.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollLisitener(sglm: GridLayoutManager) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (!recyclerView.canScrollVertically(1)) {
            onScrolledToEnd()
        }
    }

     abstract fun onScrolledToEnd()
}