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
        private var itemHeight: Float,
        private var imageHeight: Float,
        private val listener: OnViewSelectedListener<Card>
    ): RecyclerView.ViewHolder(rootView) {
        fun bind(item: Card) = with (itemView) {
            val layout = findViewById<View>(R.id.layout)
            val title = findViewById<TextView>(R.id.title)
            val image = findViewById<ImageView>(R.id.image)
            val description = findViewById<TextView>(R.id.description)

            if (itemHeight > 0) {
                val params = layoutParams as ViewGroup.MarginLayoutParams
                params.height = Math.round(itemHeight)
                layoutParams = params
            }

            if (imageHeight >= 0) {
                if (imageHeight > 0) {
                    image.visibility = View.VISIBLE
                } else { // because if we set 0 it will act like match_constraint
                    image.visibility = View.GONE
                    imageHeight = 1.0F
                }

                val params = image.layoutParams as ViewGroup.MarginLayoutParams
                params.height = Math.round(imageHeight)
                image.layoutParams = params
            }

            title.text = item.title
            description.text = item.shortDescription
            Glide.with(image).load(item.photo).apply(RequestOptions()
                .placeholder(android.R.color.darker_gray)
                .centerCrop()
                .override( // otherwise image can be shrinked
                    context.resources.getDimensionPixelSize(R.dimen.card_width),
                    context.resources.getDimensionPixelSize(R.dimen.card_image_height)
                )
            ).into(image)

            layout.transitionName = item.id.toString()
            setOnClickListener { listener.onItemSelected(item, layout) }
        }

        fun updateItemHeight(itemHeight: Float, imageHeight: Float) {
            this.itemHeight = itemHeight
            this.imageHeight = imageHeight
        }
    }

    private var itemHeight: Float = -1F
    private var imageHeight: Float = -1F

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder =
        CardViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_card, parent, false), itemHeight, imageHeight, listener)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: Card) {
        val cardHolder = holder as CardViewHolder
        cardHolder.updateItemHeight(itemHeight, imageHeight)
        cardHolder.bind(item)
    }

    fun updateItemHeight(itemHeight: Float, imageHeight: Float) {
        this.itemHeight = itemHeight
        this.imageHeight = imageHeight
    }
}