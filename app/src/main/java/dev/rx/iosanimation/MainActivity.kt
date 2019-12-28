package dev.rx.iosanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.*
import com.mintrocket.presentation.widget.FragmentContainerView
import dev.rx.iosanimation.helper.OverChangeBounds
import dev.rx.iosanimation.helper.TransitionProvider
import dev.rx.iosanimation.libria.LibriaDetailsFragment
import dev.rx.iosanimation.libria.LibriaListFragment
import dev.rx.iosanimation.sonic.MainFragment
import dev.rx.iosanimation.sonic.SecondFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        container.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.container, LibriaListFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        container.setDrawStrategy(FragmentContainerView.STRATEGY_OLD_FRONT)
        super.onBackPressed()
    }

    fun navMain() {
    }

    fun navSecond() {
        container.setDrawStrategy(FragmentContainerView.STRATEGY_NEW_FRONT)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        val currentProvider = currentFragment as TransitionProvider

        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            /*.setCustomAnimations(
                R.anim.screen_ios_enter,
                R.anim.screen_ios_exit,
                R.anim.screen_ios_pop_enter,
                R.anim.screen_ios_pop_exit
            )*/
            .replace(R.id.container, LibriaDetailsFragment.newInstance())
            .apply {
                currentProvider.transitionViews()
                    .forEach { addSharedElement(it, it.transitionName) }
            }
            .addToBackStack(null)
            .commit()
    }

}
