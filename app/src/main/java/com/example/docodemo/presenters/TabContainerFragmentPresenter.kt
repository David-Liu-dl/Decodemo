package com.example.docodemo.presenters

import com.example.docodemo.R
import com.example.docodemo.fragments.BlueFragment
import com.example.docodemo.fragments.TabContainerFragment
import com.example.docodemo.fragments.containers.RoutableContainer
import com.example.docodemo.routing.DestinationPresenter
import com.example.docodemo.routing.RoutingResult

class TabContainerFragmentPresenter : DestinationPresenter {

    override fun present(routableContainer: RoutableContainer?, callback: (RoutingResult) -> Unit) {
        if (routableContainer == null) {
            callback(RoutingResult.Failure)
        } else {
            val tabContainerFragment = TabContainerFragment.createInstance()

            routableContainer.containerFragmentManager
                .beginTransaction()
                .add(R.id.content, tabContainerFragment, BlueFragment.TAG)
                .addToBackStack(null)
                .commit()

            callback(RoutingResult.Success(tabContainerFragment))
        }
    }
}
