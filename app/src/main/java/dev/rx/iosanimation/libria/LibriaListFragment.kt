package dev.rx.iosanimation.libria

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.rx.iosanimation.MainActivity
import dev.rx.iosanimation.R
import dev.rx.iosanimation.helper.TransitionProvider
import kotlinx.android.synthetic.main.libria_list_fragment.*

class LibriaListFragment : Fragment(), TransitionProvider {


    companion object {
        fun newInstance() = LibriaListFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.libria_list_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        item1.setOnClickListener {
            (activity as MainActivity).navSecond()
        }
    }

    override fun transitionViews(): List<View> {
        return listOf(
            itemImageCard,
            itemImage,
            itemTitle,
            itemDesc,
            itemFakeBg
        )
    }

}
