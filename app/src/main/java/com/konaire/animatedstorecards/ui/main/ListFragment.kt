package com.konaire.animatedstorecards.ui.main

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.konaire.animatedstorecards.R
import com.konaire.animatedstorecards.model.Card
import com.konaire.animatedstorecards.model.ListData
import com.konaire.animatedstorecards.network.Mock
import com.konaire.animatedstorecards.ui.list.OnViewSelectedListener
import com.konaire.animatedstorecards.ui.list.SpaceDecoration
import com.konaire.animatedstorecards.ui.main.adapter.MainAdapter
import com.konaire.animatedstorecards.util.getScreenHeight

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

import kotlinx.android.synthetic.main.fragment_list_without_data.*

import java.util.concurrent.TimeUnit

/**
 * Created by Evgeny Eliseyev on 20/03/2018.
 */

class ListFragment: BaseFragment(), OnViewSelectedListener<Card> {
    private val adapter: MainAdapter by lazy {
        MainAdapter(this)
    }

    private var wasDataLoaded = false
    private var dataDisposable: Disposable? = null

    companion object {
        private const val TAG = "LIST"
        private const val KEY_TITLE = "key_title"
        private const val KEY_SUBTITLE = "key_subtitle"
        private const val KEY_BACKGROUND = "key_background"

        fun create(): ListFragment {
            val fragment = ListFragment()
            val args = Bundle()

            fragment.arguments = args
            return fragment
        }
    }

    private fun getTitle(): String = arguments?.getString(KEY_TITLE) ?: ""

    private fun setTitle(title: String) = arguments?.putString(KEY_TITLE, title)

    private fun getSubtitle(): String = arguments?.getString(KEY_SUBTITLE) ?: ""

    private fun setSubtitle(subtitle: String) = arguments?.putString(KEY_SUBTITLE, subtitle)

    private fun getBackground(): String = arguments?.getString(KEY_BACKGROUND) ?: ""

    private fun setBackground(background: String) = arguments?.putString(KEY_BACKGROUND, background)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(if (wasDataLoaded) R.layout.fragment_list_with_data else R.layout.fragment_list_without_data, container, false)

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

        updateToolbar()
        behavior.overlayTop = cardTextHeight + 2 * indent // included indents because we need to show some space in the bottom
        appbar.addOnOffsetChangedListener({ _, verticalOffset ->
            val absVerticalOffset = Math.abs(verticalOffset)
            val percentage = 1 - 1F * absVerticalOffset / listHeight
            adapter.updateItemHeight(absVerticalOffset * diffHeight + percentage * cardTextHeight, (1 - percentage) * cardImageHeight)
        })

        list.layoutParams = params
        list.addItemDecoration(SpaceDecoration(resources.getDimensionPixelSize(R.dimen.default_indent)))
        list.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        list.adapter = adapter
        reload()
    }

    override fun onStop() {
        super.onStop()
        dataDisposable?.dispose()
    }

    override fun getFragmentTag(): String = TAG

    override fun onItemSelected(item: Card, itemView: View) {
        val fragment = DetailFragment.create(item, getBackgroundBitmap())
        (activity as MainActivity).showOverlayFragment(fragment, itemView)
    }

    private fun updateToolbar() {
        title.text = getTitle()
        subtitle.text = getSubtitle()
        Glide.with(toolbarBackground).load(getBackground()).apply(
            RequestOptions().placeholder(android.R.color.transparent).centerCrop()
        ).into(toolbarBackground)
    }

    private fun hideProgress() {
        val layout = view as ConstraintLayout?
        if (layout == null || wasDataLoaded) {
            return
        }

        wasDataLoaded = true
        val set = ConstraintSet()
        set.clone(activity, R.layout.fragment_list_with_data)
        TransitionManager.beginDelayedTransition(layout)
        set.applyTo(layout)
    }

    private fun displayData(data: ListData) {
        if (view == null) {
            return
        }

        setTitle(data.title)
        setSubtitle(data.subtitle)
        setBackground(data.background)

        updateToolbar()
        adapter.reinit(data.items)
    }

    // We should use MVP architecture here. But application focuses on UI.
    // And also data isn't real. So let's skip this part.
    private fun reload() {
        dataDisposable = Observable.just(Mock.getListData()).delay(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ data ->
                hideProgress()
                displayData(data)
            }, { }, { dataDisposable = null })
    }

    // We can't use shared transition if fragment is placed in another container or it is added but not replaced.
    // So this is a small crutch to set masked screenshot of parent fragment as background of the fragment.
    private fun getBackgroundBitmap(): Bitmap {
        val width = view?.measuredWidth ?: 1
        val height = view?.measuredHeight ?: 1
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val semiTransparentBlack = Color.argb(192, 0, 0, 0)
        val canvas = Canvas(bitmap)

        view?.draw(canvas)
        canvas.drawColor(semiTransparentBlack)

        return bitmap
    }
}