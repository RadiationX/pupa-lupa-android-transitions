package dev.rx.iosanimation.sonic

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.rx.iosanimation.R
import kotlinx.android.synthetic.main.second_fragment_coord.scrollView

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.second_fragment_coord_two, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*setEnterSharedElementCallback(object :SharedElementCallback(){
            override fun onSharedElementEnd(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)

                scrollView?.apply {
                    animate().alpha(1f).start()
                }
            }
        })*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("lalala", "onViewCreated $this")

        view.postDelayed({
            scrollView?.apply {
                //animate().alpha(1f).setDuration(225).start()
            }
        }, 400)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /*shared0.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            topMargin = -scrollView.scrollY
        }
        scrollView.scrollTo(0,0)
        scrollView.requestLayout()*/
        Log.e("lalala", "onDestroyView $this")
    }
}
