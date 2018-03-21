package com.konaire.animatedstorecards.ui.list

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Evgeny Eliseyev on 21/03/2018.
 */

class SpaceDecoration(
    private val spacing: Int
): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildAdapterPosition(view)

        if (position >= 0) {
            outRect.set(if (position == 0) spacing else 0, spacing, spacing, spacing)
        } else {
            outRect.set(0, 0, 0, 0)
        }
    }
}