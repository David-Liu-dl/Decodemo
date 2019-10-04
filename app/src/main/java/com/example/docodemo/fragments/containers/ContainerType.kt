package com.example.docodemo.fragments.containers

import androidx.fragment.app.FragmentManager
import com.example.docodemo.routing.Router

interface Routable{
    val router: Router
}

interface RoutableContainer: Routable {
    sealed class ContainerType {
        object FullSize : ContainerType()
        object NonFullSize : ContainerType()
    }

    val containerFragmentManager: FragmentManager
    val containerType: ContainerType
}

interface RoutableContent: Routable {
    fun getContainer() : RoutableContainer?
}
