package com.konaire.animatedstorecards.model

/**
 * Created by Evgeny Eliseyev on 20/03/2018.
 */

data class ListData(
    val title: String,
    val subtitle: String,
    val background: String,
    val items: MutableList<Card>
)