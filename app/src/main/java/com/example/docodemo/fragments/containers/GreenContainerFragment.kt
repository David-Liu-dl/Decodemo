package com.example.docodemo.fragments.containers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.docodemo.R
import com.example.docodemo.routing.Router

class GreenContainerFragment: Fragment(), RoutableContainer {
    override val router: Router = Router.makeRouter(this)
    override val containerFragmentManager: FragmentManager = childFragmentManager
    override val containerType: RoutableContainer.ContainerType = RoutableContainer.ContainerType.FullSize

    companion object {
        const val TAG = "GreenContainerFragment"

        fun createInstance() = GreenContainerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_content, container, false)
}
