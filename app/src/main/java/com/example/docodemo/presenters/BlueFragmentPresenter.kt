package com.example.docodemo.presenters

import com.example.docodemo.R
import com.example.docodemo.fragments.BlueFragment
import com.example.docodemo.fragments.containers.RoutableContainer
import com.example.docodemo.routing.DestinationPresenter
import com.example.docodemo.routing.RoutingResult
import java.io.Serializable

class BlueFragmentPresenter(private val items: List<String>, private val localData: LocalData?) :
    DestinationPresenter {

    override fun present(routableContainer: RoutableContainer?, callback: (RoutingResult) -> Unit) {
        if (routableContainer == null) {
            callback(RoutingResult.Failure)
        }else {
            val blueFragment = BlueFragment.createInstance(
                initiallySelected = localData?.selectedIndex,
                items = items,
                selectItem = {
                    this.localData?.onClose?.invoke(it)
                    routableContainer.containerFragmentManager.popBackStack()
                }
            )

            routableContainer.containerFragmentManager
                .beginTransaction()
                .add(R.id.content, blueFragment, BlueFragment.TAG)
                .addToBackStack(null)
                .commit()

            callback(RoutingResult.Success(blueFragment))
        }
    }

    data class LocalData(val selectedIndex: Int?, val onClose: ((Int) -> Unit)?) : Serializable
}
