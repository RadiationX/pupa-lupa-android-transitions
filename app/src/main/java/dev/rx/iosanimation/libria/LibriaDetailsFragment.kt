package dev.rx.iosanimation.libria

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.transition.*
import dev.rx.iosanimation.ChangeElevation
import dev.rx.iosanimation.ChangeOutlineRadius
import dev.rx.iosanimation.R
import dev.rx.iosanimation.helper.TransitionConsumer

class LibriaDetailsFragment : Fragment(), TransitionConsumer {

    private var localTransitionViews = mutableListOf<View>()

    override var transitionsView: List<View>
        get() = localTransitionViews
        set(value) {
            localTransitionViews.clear()
            localTransitionViews.addAll(value)
        }

    companion object {
        fun newInstance() = LibriaDetailsFragment().apply {
            /*enterTransition = Fade().apply {
                duration = 2000
                transitionsView.forEach {
                    excludeTarget(it.id, true)
                }
            }*/
            /*enterTransition = AutoTransition().apply {
                duration = 2000
                transitionsView.forEach {
                    excludeTarget(it.id, true)
                }
            }*/

            val gaddamn1 = PathInterpolator(0.3f, 0.5f, 0.2f, 1f)
            /*enterTransition = TransitionSet()
                .addTransition(Fade().apply {
                    addTarget(R.id.itemTitle)
                    addTarget(R.id.itemDesc)
                })
                .apply {
                    duration = 2000
                    interpolator = gaddamn1
                }*/

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



            sharedElementEnterTransition = TransitionSet()
                .addTransition(
                    TransitionSet()
                        .addTransition(ChangeTransform().apply {
                            this.reparentWithOverlay = false
                            addTarget(R.id.itemImageCard)
                            addTarget(R.id.itemImage)
                        })
                        .addTransition(ChangeBounds().apply {
                            addTarget(R.id.itemImageCard)
                            addTarget(R.id.itemImage)
                        })
                        .addTransition(ChangeImageTransform().apply {
                            addTarget(R.id.itemImage)
                        })
                        .apply {
                            //setPathMotion(ArcMotion())
                        }
                )

                .addTransition(ChangeTransform().apply {
                    this.reparentWithOverlay = false
                    addTarget(R.id.itemTitle)
                    addTarget(R.id.itemDesc)
                    addTarget(R.id.itemFakeBg)
                })
                .addTransition(ChangeBounds().apply {
                    addTarget(R.id.itemTitle)
                    addTarget(R.id.itemDesc)
                    addTarget(R.id.itemFakeBg)
                })


                .addTransition(ChangeOutlineRadius().apply {
                    addTarget(R.id.itemImageCard)
                })
                .addTransition(ChangeElevation().apply {
                    addTarget(R.id.itemImageCard)
                })
                .apply {
                    duration = 2000
                    ordering = TransitionSet.ORDERING_TOGETHER
                    //interpolator = OvershootInterpolator(1f)
                    interpolator = gaddamn1

                }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.libria_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
