package com.konaire.animatedstorecards.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.konaire.animatedstorecards.R

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showListFragment()
        }
    }

    fun showOverlayFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.overlayContainer, fragment, fragment.getFragmentTag()
        ).addToBackStack(null).commit()
    }

    private fun showListFragment() {
        val fragment = ListFragment.create()
        supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer, fragment, fragment.getFragmentTag()
        ).commit()
    }
}
