package dev.rx.iosanimation.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import dev.rx.iosanimation.R
import kotlinx.android.synthetic.main.second_fragment.*
import kotlinx.android.synthetic.main.second_fragment.shared0
import kotlinx.android.synthetic.main.second_fragment_coord.*

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.second_fragment_coord, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("lalala", "onViewCreated $this")

        view.postDelayed({

        },1000)
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
