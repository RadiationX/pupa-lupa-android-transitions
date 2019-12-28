package com.mintrocket.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat

class FragmentContainerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRef: Int = 0
) : FrameLayout(context, attrs, defStyleRef) {

    companion object {
        const val STRATEGY_NONE = 0
        const val STRATEGY_NEW_FRONT = 1
        const val STRATEGY_OLD_FRONT = 2
    }

    private var currentDrawStrategy = STRATEGY_NONE

    private val hierarchyChangeListener = object : OnHierarchyChangeListener {
        override fun onChildViewRemoved(parent: View, child: View) {
            when (currentDrawStrategy) {
                STRATEGY_NEW_FRONT -> strategyExit(child)
                STRATEGY_OLD_FRONT -> strategyEnter(child)
            }
        }

        override fun onChildViewAdded(parent: View, child: View) {
            when (currentDrawStrategy) {
                STRATEGY_NEW_FRONT -> strategyEnter(child)
                STRATEGY_OLD_FRONT -> strategyExit(child)
            }
        }
    }

    init {
        setOnHierarchyChangeListener(hierarchyChangeListener)
    }

    @RequiresApi(20)
    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.dispatchApplyWindowInsets(WindowInsets(insets))
        }
        return insets
    }

    fun setDrawStrategy(strategy: Int) {
        currentDrawStrategy = strategy
    }

    private fun strategyEnter(child: View) {
        child.bringToFront()
        ViewCompat.setTranslationZ(child, 16f)
    }

    private fun strategyExit(child: View) {
        ViewCompat.setTranslationZ(child, 0f)
    }

}