package com.konaire.animatedstorecards.ui.main

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.konaire.animatedstorecards.R
import com.konaire.animatedstorecards.model.Card

import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * Created by Evgeny Eliseyev on 20/03/2018.
 */

class DetailFragment: BaseFragment() {
    companion object {
        private const val TAG = "DETAIL"
        private const val KEY_CARD = "key_card"

        fun create(card: Card): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()

            args.putParcelable(KEY_CARD, card)
            fragment.arguments = args
            return fragment
        }
    }

    private fun getCard(): Card = arguments?.getParcelable(KEY_CARD) ?: Card()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val card = getCard()
        title.text = card.title
        description.text = card.fullDescription
        description.movementMethod = ScrollingMovementMethod()
        Glide.with(image).load(card.photo).apply(RequestOptions()
            .placeholder(android.R.color.darker_gray)
            .centerCrop()
        ).into(image)
    }

    override fun getFragmentTag(): String = TAG
}