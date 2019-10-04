package com.example.docodemo.mappers

import com.example.docodemo.presenters.BlueFragmentPresenter
import com.example.docodemo.presenters.GreenFragmentPresenter
import com.example.docodemo.presenters.RedFragmentPresenter
import com.example.docodemo.presenters.TabContainerFragmentPresenter
import com.example.docodemo.routing.AppDestination
import com.example.docodemo.routing.DestinationPresenter
import java.lang.RuntimeException

enum class Destination {
    TAB,
    RED,
    BLUE,
    GREEN,
    PURPLE,
}

object AppDestinationToPresenterMapper {

    fun getDestinationPresenter(appDestination: AppDestination): DestinationPresenter? {
        val getDestinationPresenter: (Destination) -> DestinationPresenter? = {
            getDestinationPresenter(
                it,
                appDestination.destinationModel.data,
                appDestination.localData
            )
        }

        return Destination.valueOf(appDestination.destinationModel.type).let(getDestinationPresenter)
    }

    private fun getDestinationPresenter(
        destination: Destination,
        data: Any?,
        localData: Any?
    ): DestinationPresenter? {
        return when (destination) {
            Destination.TAB -> TabContainerFragmentPresenter()
            Destination.RED -> RedFragmentPresenter()
            Destination.GREEN -> GreenFragmentPresenter()
            Destination.BLUE -> BlueFragmentPresenter(
                (data as? List<String>) ?: emptyList(),
                null
            )
            else -> throw RuntimeException("Oh god!")
        }
    }
}
