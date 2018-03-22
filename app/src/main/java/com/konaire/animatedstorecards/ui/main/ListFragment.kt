package com.konaire.animatedstorecards.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.konaire.animatedstorecards.R
import com.konaire.animatedstorecards.model.Card
import com.konaire.animatedstorecards.model.ListData
import com.konaire.animatedstorecards.ui.list.OnViewSelectedListener
import com.konaire.animatedstorecards.ui.list.SpaceDecoration
import com.konaire.animatedstorecards.ui.main.adapter.MainAdapter
import com.konaire.animatedstorecards.util.getScreenHeight

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

import kotlinx.android.synthetic.main.fragment_list.*

import java.util.concurrent.TimeUnit

/**
 * Created by Evgeny Eliseyev on 20/03/2018.
 */

class ListFragment: Fragment(), OnViewSelectedListener<Card> {
    private val adapter: MainAdapter by lazy {
        MainAdapter(this)
    }

    companion object {
        const val TAG = "LIST"
        fun create(): ListFragment = ListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val params = list.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as AppBarLayout.ScrollingViewBehavior
        val indent = resources.getDimensionPixelSize(R.dimen.default_indent)
        val toolbarHeight = resources.getDimensionPixelSize(R.dimen.toolbar_height)
        val cardImageHeight = resources.getDimensionPixelSize(R.dimen.card_image_height)
        val listHeight = activity!!.getScreenHeight() - toolbarHeight
        val cardHeight = listHeight - 2 * indent
        val diffHeight = 1F * cardHeight / listHeight
        val cardTextHeight = cardHeight - cardImageHeight

        behavior.overlayTop = cardTextHeight + 2 * indent // included indents because we need to show some space in the bottom
        appbar.addOnOffsetChangedListener({ _, verticalOffset ->
            val absVerticalOffset = Math.abs(verticalOffset)
            val diffOffset = 1F * (listHeight - absVerticalOffset) / listHeight
            adapter.updateItemHeight(absVerticalOffset * diffHeight + cardTextHeight * diffOffset, cardImageHeight * (1 - diffOffset))
        })

        list.addItemDecoration(SpaceDecoration(resources.getDimensionPixelSize(R.dimen.default_indent)))
        list.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        list.adapter = adapter
        reload()
    }

    override fun onItemSelected(item: Card) {
        // TODO: Show detail
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
    }

    private fun displayData(data: ListData) {
        Glide.with(toolbarBackground).load(data.background).apply(
            RequestOptions().placeholder(R.color.colorPrimary).centerCrop()
        ).into(toolbarBackground)
        adapter.reinit(data.items)
    }

    // TODO: Add MVP + CA
    private fun reload() {
        showProgress()
        BehaviorSubject.createDefault(
            ListData(
                "La Delice",
                "Pastry Shop",
                "https://aqvaparkatoll.ru/public/files/meals/2015/09/fd634e119.png",
                arrayListOf(
                    Card(
                        "http://www.sentidoorkalotusbeach.com/Uploaded/Content/565fb531-3896-4788-9619-bff348df9f71.jpg",
                        "About us",
                        "A restaurant, or an eatery, is a business which prepares and serves food and drinks to customers in exchange for money.",
                        "A restaurant, or an eatery, is a business which prepares and serves food and drinks to customers in exchange for money. Meals are generally served and eaten on the premises, but many restaurants also offer take-out and food delivery services, and some offer only take-out and delivery. Restaurants vary greatly in appearance and offerings, including a wide variety of cuisines and service models ranging from inexpensive fast food restaurants and cafeterias to mid-priced family restaurants, to high-priced luxury establishments. In Western countries, most mid- to high-range restaurants serve alcoholic beverages such as beer and wine. Some restaurants serve all the major meals, such as breakfast, lunch, and dinner (e.g., major fast food chains, diners, hotel restaurants, and airport restaurants). Other restaurants may only serve a single meal (e.g., a pancake house may only serve breakfast) or they may serve two meals (e.g., lunch and dinner) or even a kids' meal."
                    ), Card(
                        "http://infoindustria.com.ua/wp-content/uploads/2016/08/Ovoshhi-i-fruktyi.jpg",
                        "Menu",
                        "Restaurants are classified or distinguished in many different ways. The primary factors are usually the food itself.",
                        "Restaurants are classified or distinguished in many different ways. The primary factors are usually the food itself (e.g. vegetarian, seafood, steak); the cuisine (e.g. Italian, Chinese, Japanese, Indian, French, Mexican, Thai) or the style of offering (e.g. tapas bar, a sushi train, a tastet restaurant, a buffet restaurant or a yum cha restaurant). Beyond this, restaurants may differentiate themselves on factors including speed (see fast food), formality, location, cost, service, or novelty themes (such as automated restaurants). Restaurants range from inexpensive and informal lunching or dining places catering to people working nearby, with modest food served in simple settings at low prices, to expensive establishments serving refined food and fine wines in a formal setting. In the former case, customers usually wear casual clothing. In the latter case, depending on culture and local traditions, customers might wear semi-casual, semi-formal or formal wear. Typically, at mid- to high-priced restaurants, customers sit at tables, their orders are taken by a waiter, who brings the food when it is ready. After eating, the customers then pay the bill. In some restaurants, such as workplace cafeterias, there are no waiters; the customers use trays, on which they place cold items that they select from a refrigerated container and hot items which they request from cooks, and then they pay a cashier before they sit down. Another restaurant approach which uses few waiters is the buffet restaurant. Customers serve food onto their own plates and then pay at the end of the meal. Buffet restaurants typically still have waiters to serve drinks and alcoholic beverages. Fast food restaurants are also considered a restaurant."
                    ), Card(
                        "http://classpic.ru/wp-content/uploads/krasivo-oformlennyj-desert.jpg",
                        "Delivery",
                        "Restaurant guides review restaurants, often ranking them or providing information to guide consumers.",
                        "Restaurant guides review restaurants, often ranking them or providing information to guide consumers (type of food, handicap accessibility, facilities, etc.). One of the most famous contemporary guides is the Michelin series of guides which accord from 1 to 3 stars to restaurants they perceive to be of high culinary merit. Restaurants with stars in the Michelin guide are formal, expensive establishments; in general the more stars awarded, the higher the prices."
                    )
                )
            )
        ).delay(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe({ data ->
            hideProgress()
            displayData(data)
        })
    }
}