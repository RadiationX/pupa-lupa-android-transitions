package dev.rx.iosanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import android.view.View
import android.view.animation.*
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import dev.rx.iosanimation.ui.main.MainFragment
import dev.rx.iosanimation.ui.main.SecondFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        container.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    fun navMain() {
    }

    fun navSecond() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        val sharedView = currentFragment?.view?.findViewById<View>(R.id.shared)
        val sharedView1 = currentFragment?.view?.findViewById<View>(R.id.shared1)
        val sharedView2 = currentFragment?.view?.findViewById<View>(R.id.shared2)
        val sharedView3 = currentFragment?.view?.findViewById<View>(R.id.shared3)
        val sharedView0 = currentFragment?.view?.findViewById<View>(R.id.shared0)
        val sharedView4 = currentFragment?.view?.findViewById<View>(R.id.shared4)
        val sharedView5 = currentFragment?.view?.findViewById<View>(R.id.shared5)
        val sharedView6 = currentFragment?.view?.findViewById<View>(R.id.shared6)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, SecondFragment.newInstance().apply {


                val gaddamn = object : OvershootInterpolator(1f) {
                    val path = PathInterpolator(.64f, -0.51f, .22f, 1.52f)
                    val path1 = AccelerateDecelerateInterpolator()
                    val path2 = DecelerateInterpolator()
                    val path3 = PathInterpolator(0.3f, 0.5f, 0.2f, 1f)
                    val path4 = PathInterpolator(0.5f, 1.17f, 0.45f, 1.09f)
                    override fun getInterpolation(input: Float): Float {
                        return path4.getInterpolation(input)
                    }
                }

                val gaddamn1 = PathInterpolator(0.3f, 0.5f, 0.2f, 1f)


                sharedElementEnterTransition = TransitionSet()
                    .addTransition(ChangeBounds().apply {
                        addTarget(R.id.shared)
                        addTarget(R.id.shared2)
                        addTarget(R.id.shared3)
                        addTarget(R.id.shared1)
                        addTarget(R.id.shared0)
                        addTarget(R.id.shared4)
                        addTarget(R.id.shared5)
                        addTarget(R.id.shared6)
                        interpolator = gaddamn
                    })
                    .addTransition(ChangeOutlineRadius().apply {
                        addTarget(R.id.shared)
                        interpolator = gaddamn
                    })
                    .addTransition(ChangeOutlineRadius().apply {
                        addTarget(R.id.shared6)
                        interpolator = gaddamn
                    })
                    .addTransition(ChangeElevation().apply {
                        addTarget(R.id.shared)
                        interpolator = FastOutSlowInInterpolator()
                    })
                    .apply {
                        duration = 400
                        ordering = TransitionSet.ORDERING_TOGETHER
                        //interpolator = OvershootInterpolator(1f)
                    }

            })
            .apply {
                sharedView?.also {
                    addSharedElement(it, "shared")
                }
                sharedView1?.also {
                    addSharedElement(it, "shared1")
                }
                sharedView2?.also {
                    addSharedElement(it, "shared2")
                }
                sharedView3?.also {
                    addSharedElement(it, "shared3")
                }


                sharedView0?.also {
                    addSharedElement(it, "shared0")
                }

                sharedView4?.also {
                    addSharedElement(it, "shared4")
                }

                sharedView5?.also {
                    addSharedElement(it, "shared5")
                }


                sharedView6?.also {
                    addSharedElement(it, "shared6")
                }
            }
            .addToBackStack(null)
            .commit()
    }

}
