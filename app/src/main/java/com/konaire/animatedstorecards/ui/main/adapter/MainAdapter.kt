package com.konaire.animatedstorecards.ui.main.adapter

import com.konaire.animatedstorecards.model.Card
import com.konaire.animatedstorecards.ui.list.BaseAdapter
import com.konaire.animatedstorecards.ui.list.ListItemType
import com.konaire.animatedstorecards.ui.list.OnViewSelectedListener

/**
 * Created by Evgeny Eliseyev on 20/03/2018.
 */

class MainAdapter(
    listener: OnViewSelectedListener<Card>
): BaseAdapter<Card>(listener) {
    init {
        delegateAdapters[ListItemType.Card.ordinal] = CardDelegateAdapter(listener)
    }

    fun updateItemHeight(itemHeight: Float, imageHeight: Float) {
        for (adapter in delegateAdapters.values) {
            if (adapter is CardDelegateAdapter) {
                adapter.updateItemHeight(itemHeight, imageHeight)
            }
        }

        notifyDataSetChanged()
    }
}