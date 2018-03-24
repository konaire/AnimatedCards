package com.konaire.animatedstorecards.ui.main

import android.support.v4.app.Fragment

/**
 * Created by Evgeny Eliseyev on 24/03/2018.
 */

abstract class BaseFragment: Fragment() {
    abstract fun getFragmentTag(): String
}