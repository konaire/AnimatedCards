package com.konaire.animatedstorecards.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.konaire.animatedstorecards.R
import com.konaire.animatedstorecards.model.Card
import com.konaire.animatedstorecards.ui.list.DelegateAdapter
import com.konaire.animatedstorecards.ui.list.OnViewSelectedListener

/**
 * Created by Evgeny Eliseyev on 20/03/2018.
 */

class CardDelegateAdapter(
    private val listener: OnViewSelectedListener<Card>
): DelegateAdapter<Card> {
    class CardViewHolder(
        rootView: View,
        private val listener: OnViewSelectedListener<Card>
    ): RecyclerView.ViewHolder(rootView) {
        fun bind(item: Card) = with (itemView) {
            val title = findViewById<TextView>(R.id.title)
            val image = findViewById<ImageView>(R.id.image)
            val description = findViewById<TextView>(R.id.description)

            title.text = item.title
            description.text = item.shortDescription
            Glide.with(image).load(item.photo).apply(
                RequestOptions().placeholder(android.R.color.darker_gray).centerCrop()
            ).into(image)

            setOnClickListener { listener.onItemSelected(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder =
        CardViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_card, parent, false), listener)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: Card) =
        (holder as CardViewHolder).bind(item)
}