package com.konaire.animatedstorecards.ui.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Evgeny Eliseyev on 20/03/2018.
 */

abstract class BaseAdapter<T>(
    protected val listener: OnViewSelectedListener<T>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() where T: ViewType {
    protected var delegateAdapters: MutableMap<Int, DelegateAdapter<T>> = HashMap()
    private var items: MutableList<T> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegateAdapters[viewType]!!.onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        delegateAdapters[getItemViewType(position)]!!.onBindViewHolder(holder, items[position])

    override fun getItemViewType(position: Int): Int = items[position].getViewType()

    override fun getItemCount(): Int = items.size

    open fun reinit(data: MutableList<T>) {
        items = data
        notifyDataSetChanged()
    }
}