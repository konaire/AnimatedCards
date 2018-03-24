package com.konaire.animatedstorecards.ui.list

import android.view.View

/**
 * Created by Evgeny Eliseyev on 20/03/2018.
 */

interface OnViewSelectedListener<in T> where T: ViewType {
    fun onItemSelected(item: T, itemView: View)
}