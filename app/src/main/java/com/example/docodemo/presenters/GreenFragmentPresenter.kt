package com.example.docodemo.presenters

import com.example.docodemo.R
import com.example.docodemo.fragments.GreenFragment
import com.example.docodemo.fragments.containers.RoutableContainer
import com.example.docodemo.routing.DestinationPresenter
import com.example.docodemo.routing.RoutingResult

class GreenFragmentPresenter : DestinationPresenter {
    override fun present(routableContainer: RoutableContainer?, callback: (RoutingResult) -> Unit) {
        if (routableContainer == null) {
            callback(RoutingResult.Failure)

            return
        } else {
            with(routableContainer.containerFragmentManager) {
                beginTransaction()
                    .run {
                        fragments.forEach { hide(it) }
                        findFragmentByTag(GreenFragment.TAG)
                            ?.run { show(this) }
                            ?: add(R.id.content, GreenFragment.createInstance(), GreenFragment.TAG)
                    }.commit()

//            routableContainer.containerFragmentManager
//                .beginTransaction()
//                .setCustomAnimations(
//                    R.anim.slide_in_right,
//                    R.anim.slide_out_left,
//                    android.R.anim.slide_in_left,
//                    android.R.anim.slide_out_right
//                )
//                .replace(R.id.content, greenFragment, RedFragment.TAG)
//                .addToBackStack(null)
//                .commit()
//
//            callback(RoutingResult.Success(greenFragment))
            }
        }
    }
}
