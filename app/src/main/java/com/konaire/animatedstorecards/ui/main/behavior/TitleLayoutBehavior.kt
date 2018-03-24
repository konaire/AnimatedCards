package com.konaire.animatedstorecards.ui.main.behavior

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

import com.konaire.animatedstorecards.R

/**
 * Created by Evgeny Eliseyev on 22/03/2018.
 */

@Suppress("unused")
class TitleLayoutBehavior(
    context: Context,
    attrs: AttributeSet
): CoordinatorLayout.Behavior<ConstraintLayout>(context, attrs) {
    private var startHeight: Float
    private var finalHeight: Float
    private val startVerticalBias: Float
    private val finalVerticalBias: Float
    private val startSubtitleHorizontalBias: Float
    private val finalSubtitleHorizontalBias: Float

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.ConstraintLayout)

        startHeight = a.getDimension(R.styleable.ConstraintLayout_startHeight, -1F)
        finalHeight = a.getDimension(R.styleable.ConstraintLayout_finalHeight, -1F)
        startVerticalBias = a.getFloat(R.styleable.ConstraintLayout_startVerticalBias, 0F)
        finalVerticalBias = a.getFloat(R.styleable.ConstraintLayout_finalVerticalBias, 0F)
        startSubtitleHorizontalBias = a.getFloat(R.styleable.ConstraintLayout_startSubtitleHorizontalBias, 0F)
        finalSubtitleHorizontalBias = a.getFloat(R.styleable.ConstraintLayout_finalSubtitleHorizontalBias, 0F)

        a.recycle()
    }

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: ConstraintLayout?, dependency: View?): Boolean = dependency is AppBarLayout

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: ConstraintLayout, dependency: View): Boolean {
        reinitHeight(parent)
        val title = child.findViewById<View>(R.id.title)
        val subtitle = child.findViewById<View>(R.id.subtitle)
        val maxScroll = (dependency as AppBarLayout).totalScrollRange
        val percentage = 1 - 1F * Math.abs(dependency.y) / maxScroll

        val titleParams = title.layoutParams as ConstraintLayout.LayoutParams
        titleParams.verticalBias = startVerticalBias + percentage * (finalVerticalBias - startVerticalBias)
        title.layoutParams = titleParams

        val subtitleParams = subtitle.layoutParams as ConstraintLayout.LayoutParams
        subtitleParams.horizontalBias = startSubtitleHorizontalBias + percentage * (finalSubtitleHorizontalBias - startSubtitleHorizontalBias)
        subtitle.layoutParams = subtitleParams

        val childParams = child.layoutParams
        childParams.height = Math.round(startHeight + percentage * (finalHeight - startHeight))
        child.layoutParams = childParams
        return true
    }

    private fun reinitHeight(parent: CoordinatorLayout) {
        if (startHeight < 0) {
            startHeight = parent.measuredHeight.toFloat()
        }

        if (finalHeight < 0) {
            finalHeight = parent.measuredHeight.toFloat()
        }
    }
}