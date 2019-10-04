package com.example.docodemo.fragments.containers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.docodemo.R
import com.example.docodemo.fragments.TabContainerFragment
import com.example.docodemo.routing.Router.Companion.makeRouter

class ContainerFragment : Fragment(), RoutableContainer {
    override val containerFragmentManager: FragmentManager by lazy { childFragmentManager }
    override val router = makeRouter(this)
    override val containerType: RoutableContainer.ContainerType =
        RoutableContainer.ContainerType.FullSize

    companion object {
        const val TAG = "ContainerFragment"

        fun createInstance() = ContainerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_content, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        containerFragmentManager.beginTransaction()
            .replace(R.id.content, TabContainerFragment.createInstance())
            .commit()
    }
}
