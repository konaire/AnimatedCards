package com.konaire.animatedstorecards.ui.main

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.transition.ChangeBounds
import android.support.transition.ChangeTransform
import android.support.transition.TransitionSet
import android.view.View

import com.konaire.animatedstorecards.R
import com.konaire.animatedstorecards.ui.BaseFragment

class MainActivity: AppCompatActivity() {
    lateinit var listBackground: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showListFragment()
        }
    }

    fun showOverlayFragment(fragment: BaseFragment, view: View) {
        fragment.sharedElementEnterTransition = getTransitionSet()
        fragment.sharedElementReturnTransition = getTransitionSet()

        supportFragmentManager.beginTransaction()
            .addSharedElement(view, view.transitionName)
            .replace(R.id.mainContainer, fragment, fragment.getFragmentTag())
            .addToBackStack(null)
            .commit()
    }

    private fun showListFragment() {
        val fragment = ListFragment.create()
        supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer, fragment, fragment.getFragmentTag()
        ).commit()
    }

    private fun getTransitionSet() =
        TransitionSet()
            .addTransition(ChangeBounds())
            .addTransition(ChangeTransform())
            .setOrdering(TransitionSet.ORDERING_TOGETHER)
}
