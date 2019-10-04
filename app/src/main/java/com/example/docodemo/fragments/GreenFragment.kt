package com.example.docodemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.docodemo.R
import com.example.docodemo.fragments.containers.RoutableContainer
import com.example.docodemo.fragments.containers.RoutableContent
import com.example.docodemo.routing.Router
import kotlinx.android.synthetic.main.layout_toolbar.*

class GreenFragment : Fragment(), RoutableContent {
    override val router: Router by lazy { Router.makeRouter(getContainer()) }

    companion object {
        const val TAG = "GreenFragment"

        fun createInstance() = GreenFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_green, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        toolbar_actionbar.setTitle(R.string.fragment_label_green)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar_actionbar)
    }

    override fun getContainer(): RoutableContainer? = getContainerFragment(parentFragment)

    private fun getContainerFragment(parentFragment: Fragment?): RoutableContainer?{
        if (parentFragment != null) {
            return (parentFragment as? RoutableContainer)
                .let { it } ?: getContainerFragment(parentFragment)
        }

        return null
    }
}
