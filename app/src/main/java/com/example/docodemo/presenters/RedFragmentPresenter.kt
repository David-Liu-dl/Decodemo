package com.example.docodemo.presenters

import com.example.docodemo.R
import com.example.docodemo.fragments.RedFragment
import com.example.docodemo.fragments.containers.RoutableContainer
import com.example.docodemo.routing.DestinationPresenter
import com.example.docodemo.routing.RoutingResult

class RedFragmentPresenter : DestinationPresenter {
    override fun present(routableContainer: RoutableContainer?, callback: (RoutingResult) -> Unit) {
        if (routableContainer == null) {
            callback(RoutingResult.Failure)

            return
        } else {
            with(routableContainer.containerFragmentManager) {
                beginTransaction()
                    .run {
                        fragments.forEach { hide(it) }
                        findFragmentByTag(RedFragment.TAG)
                            ?.run { show(this) }
                            ?: add(R.id.content, RedFragment.createInstance(), RedFragment.TAG)
                    }.commit()

//            routableContainer.containerFragmentManager
//                .beginTransaction()
//                .setCustomAnimations(
//                    R.anim.slide_in_right,
//                    R.anim.slide_out_left,
//                    android.R.anim.slide_in_left,
//                    android.R.anim.slide_out_right
//                )
//                .replace(R.id.content, redFragment, RedFragment.TAG)
//                .addToBackStack(null)
//                .commit()
            }
        }
    }
}
