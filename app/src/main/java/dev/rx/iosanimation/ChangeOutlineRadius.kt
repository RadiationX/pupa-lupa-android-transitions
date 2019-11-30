package dev.rx.iosanimation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Outline
import android.os.Build
import android.transition.Transition
import android.transition.TransitionValues
import android.util.AttributeSet
import android.util.Log
import android.util.Property
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.cardview.widget.CardView

/**
 * Transitions a view from [startRadius] to [endRadius] through a [ViewOutlineProvider].
 * Note that if the view already has a radius (rounded corners), [startRadius] should match this value
 *
 * @author Stefan de Bruijn
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class ChangeOutlineRadius : Transition {

    private companion object {
        /**
         * Unique key for our start and end values to be kept in [TransitionValues]
         */
        private const val RADIUS = "ChangeOutlineRadius:radius"

        /**
         * The properties from [TransitionValues] we care about.
         * If the [TransitionValues] from [captureStartValues] and [captureEndValues] do not differ on this property,
         * this [Transition] will not run
         */
        private val PROPERTIES = arrayOf(RADIUS)

        /**
         * Animator property which will set a rounded outline through a [ViewOutlineProvider]
         */
        private object OutlineRadiusProperty :
            Property<View, Float>(Float::class.java, "outlineRadius") {
            override fun get(view: View): Float {
                return (view as? CardView)?.radius ?: 0f
            }

            override fun set(view: View, value: Float) {
                (view as? CardView)?.radius = value.coerceAtLeast(0f)
            }
        }
    }

    constructor() : super()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun getTransitionProperties(): Array<String> {
        return PROPERTIES
    }

    override fun captureStartValues(transitionValues: TransitionValues) {
        // Here we get the radius our transition starts with.
        // Note that this can be the starting value for both the original transition *and the reverse*
        val view = transitionValues.view
        Log.e("lalala", "captureStartValues ${(view as? CardView)?.radius}")
        transitionValues.values[RADIUS] = (view as? CardView)?.radius ?: 0
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        // Here we get the radius our transition ends with.
        // Note that this can be the ending value for both the original transition *and the reverse*
        val view = transitionValues.view
        Log.e("lalala", "captureEndValues ${(view as? CardView)?.radius}")
        transitionValues.values[RADIUS] = (view as? CardView)?.radius ?: 0
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues,
        endValues: TransitionValues
    ): Animator? {
        // The view on the start and end values is the same, so it doesn't matter which we use
        val view = endValues.view
        // We want the view to clip to the round rect we're going to set

        // The startRadius/endRadius we are going to animate from/to.
        // Using the start/end value instead of the constructor params allows us to support reversing this animation too
        val sR = startValues.values[RADIUS] as Float
        val eR = endValues.values[RADIUS] as Float

        Log.e(
            "lalala",
            "createAnimator $sR, $eR, ${(view as? CardView)?.radius}"
        )
        // Animator with Property allows us to adjust properties that aren't directly (available) on the View itself
        return ObjectAnimator.ofFloat(view, OutlineRadiusProperty, sR, eR)
    }
}