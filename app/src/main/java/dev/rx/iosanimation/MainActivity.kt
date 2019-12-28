package dev.rx.iosanimation

import android.content.Context
import android.graphics.Matrix
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.view.animation.*
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.*
import com.mintrocket.presentation.widget.FragmentContainerView
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
                .setReorderingAllowed(true)
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
                    super.onFragmentDestroyed(fm, f)

                    //container.setDrawStrategy(FragmentContainerView.STRATEGY_OLD_FRONT)
                }
            },
            false
        )
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
        val sharedView = currentFragment?.view?.findViewById<View>(R.id.shared)
        val sharedView1 = currentFragment?.view?.findViewById<View>(R.id.shared1)
        val sharedView2 = currentFragment?.view?.findViewById<View>(R.id.shared2)
        val sharedView3 = currentFragment?.view?.findViewById<View>(R.id.shared3)
        val sharedView0 = currentFragment?.view?.findViewById<View>(R.id.shared0)
        val sharedView4 = currentFragment?.view?.findViewById<View>(R.id.shared4)
        val sharedView5 = currentFragment?.view?.findViewById<View>(R.id.shared5)
        val sharedView6 = currentFragment?.view?.findViewById<View>(R.id.shared6)

        val sharedViews = listOf(
            sharedView,
            sharedView1,
            sharedView2,
            sharedView3,
            sharedView0,
            sharedView4,
            sharedView5,
            sharedView6
        )
        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            /*.setCustomAnimations(
                R.anim.screen_ios_enter,
                R.anim.screen_ios_exit,
                R.anim.screen_ios_pop_enter,
                R.anim.screen_ios_pop_exit
            )*/
            .replace(R.id.container, SecondFragment.newInstance().apply {
                //return@apply
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
                    .addTransition(ChangeTransform().apply {
                        this.reparentWithOverlay = false
                        addTarget(R.id.shared)
                        addTarget(R.id.shared2)
                        addTarget(R.id.shared3)
                        addTarget(R.id.shared1)
                        addTarget(R.id.shared0)
                        addTarget(R.id.shared4)
                        addTarget(R.id.shared5)
                        addTarget(R.id.shared6)

                    })
                    .addTransition(ChangeBounds().apply {
                        addTarget(R.id.shared)
                        addTarget(R.id.shared2)
                        addTarget(R.id.shared3)
                        addTarget(R.id.shared1)
                        addTarget(R.id.shared0)
                        addTarget(R.id.shared4)
                        addTarget(R.id.shared5)
                        addTarget(R.id.shared6)
                    })
                    .addTransition(ChangeOutlineRadius().apply {
                        addTarget(R.id.shared)
                    })
                    .addTransition(ChangeOutlineRadius().apply {
                        addTarget(R.id.shared6)
                    })
                    .addTransition(ChangeOutlineRadius().apply {
                        addTarget(R.id.shared4)
                    })
                    /*.addTransition(ChangeElevation().apply {
                        addTarget(R.id.shared)
                    })
                    .addTransition(ChangeElevation().apply {
                        addTarget(R.id.shared6)
                    })*/
                    .apply {
                        duration = 4000
                        ordering = TransitionSet.ORDERING_TOGETHER
                        //interpolator = OvershootInterpolator(1f)
                        interpolator = gaddamn1

                    }

                /*sharedElementReturnTransition = */TransitionSet()
                .addTransition(ChangeBounds().apply {

                    addTarget(R.id.shared)
                    /*addTarget(R.id.shared)
                    addTarget(R.id.shared2)
                    addTarget(R.id.shared3)
                    addTarget(R.id.shared1)
                    addTarget(R.id.shared0)
                    addTarget(R.id.shared4)
                    addTarget(R.id.shared5)
                    addTarget(R.id.shared6)*/
                    interpolator = gaddamn1
                })
                .addTransition(ChangeBounds().apply {
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
                })
                .apply {
                    duration = 4000
                    ordering = TransitionSet.ORDERING_TOGETHER
                    //interpolator = OvershootInterpolator(1f)
                    interpolator = gaddamn

                }

                /*sharedElementReturnTransition = */TransitionSet()
                .addTransition(OverChangeBounds().apply {

                    addTarget(R.id.shared)
                    /*addTarget(R.id.shared)
                    addTarget(R.id.shared2)
                    addTarget(R.id.shared3)
                    addTarget(R.id.shared1)
                    addTarget(R.id.shared0)
                    addTarget(R.id.shared4)
                    addTarget(R.id.shared5)
                    addTarget(R.id.shared6)*/
                    interpolator = gaddamn1
                })
                .addTransition(ChangeBounds().apply {
                    addTarget(R.id.shared2)
                    addTarget(R.id.shared3)
                    addTarget(R.id.shared1)
                    addTarget(R.id.shared0)
                    addTarget(R.id.shared4)
                    addTarget(R.id.shared5)
                    addTarget(R.id.shared6)
                    interpolator = gaddamn1
                })
                /*.addTransition(ChangeOutlineRadius().apply {
                    addTarget(R.id.shared)
                    interpolator = gaddamn1
                })
                .addTransition(ChangeOutlineRadius().apply {
                    addTarget(R.id.shared6)
                    interpolator = gaddamn1
                })
                .addTransition(ChangeElevation().apply {
                    addTarget(R.id.shared)
                    interpolator = FastOutSlowInInterpolator()
                })*/
                .apply {
                    duration = 4000
                    ordering = TransitionSet.ORDERING_TOGETHER
                    //interpolator = OvershootInterpolator(1f)
                }

            })
            .apply {
                sharedViews.mapNotNull { it }.forEach { addSharedElement(it, it.transitionName) }

                /*sharedView?.also {
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
                }*/
            }
            .addToBackStack(null)
            .commit()
    }

    private fun getSharedTransition(): Transition = TransitionSet().apply {
        addTransition(ChangeImageTransform())
        addTransition(ChangeBounds())
        addTransition(ChangeTransform())
        addTransition(ChangeClipBounds())
        //setPathMotion(ArcMotion())
        interpolator = FastOutSlowInInterpolator()
        //duration = 2250
    }

}
