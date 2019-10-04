package com.example.docodemo.routing

import androidx.fragment.app.Fragment
import com.example.docodemo.fragments.containers.RoutableContainer
import com.example.docodemo.mappers.AppDestinationToPresenterMapper

interface DestinationPresenter {
    fun present(routableContainer: RoutableContainer?, callback: (RoutingResult) -> Unit)
}

class Router (
    private val routableContainer: RoutableContainer?,
    private val destinationPresenterGetter: (AppDestination) -> DestinationPresenter?
) {

    companion object {
        fun makeRouter(source: RoutableContainer?): Router = Router(source, AppDestinationToPresenterMapper::getDestinationPresenter)
    }

    fun navigate(destination: AppDestination) {
        navigate(destination) {

        }
    }

//    fun navigate(routes: List<AppDestination>) {
//        routes
//            .first()
//            .let {
//                navigate(it) { result ->
//                    when (result) {
//                        is RoutingResult.Success -> makeRouter(result.destinationFragment).navigate(
//                            routes.takeLast(routes.size - 1)
//                        )
//                        is RoutingResult.Failure -> {
//                        }
//                    }
//                }
//            }
//    }

    private fun navigate(destination: AppDestination, callback: (RoutingResult) -> Unit) {
        destinationPresenterGetter(destination)?.present(routableContainer, callback)
    }
}

sealed class RoutingResult {
    data class Success(val destinationFragment: Fragment) : RoutingResult()
    object Failure : RoutingResult()
}

data class AppDestination(val destinationModel: DestinationModel, val localData: Any?)

data class DestinationModel(val type: String, val data: Any?)
