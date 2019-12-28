package dev.rx.iosanimation.helper

import android.view.View

interface TransitionProvider {
    fun transitionViews(): List<View>
}