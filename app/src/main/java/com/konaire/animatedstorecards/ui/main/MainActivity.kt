package com.konaire.animatedstorecards.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.konaire.animatedstorecards.R

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showListFragment()
        }
    }

    private fun showListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, ListFragment.create(), ListFragment.TAG).commit()
    }
}
