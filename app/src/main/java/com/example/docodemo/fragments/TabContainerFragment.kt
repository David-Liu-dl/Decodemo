package com.example.docodemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.docodemo.R
import com.example.docodemo.fragments.containers.*
import com.example.docodemo.mappers.Destination
import com.example.docodemo.routing.AppDestination
import com.example.docodemo.routing.DestinationModel
import com.example.docodemo.routing.Router
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_tab.*

class TabContainerFragment : Fragment(),
    RoutableContainer,
    RoutableContent,
    Routable,
    BottomNavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemReselectedListener {

    override val containerFragmentManager: FragmentManager by lazy { childFragmentManager }
    override val router: Router by lazy { Router.makeRouter(this) }
    override val containerType: RoutableContainer.ContainerType =
        RoutableContainer.ContainerType.NonFullSize

    companion object {
        const val TAG = "TabContainerFragment"

        fun createInstance() = TabContainerFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigation.setOnNavigationItemSelectedListener(this)
        navigation.setOnNavigationItemReselectedListener(this)
        navigation.selectedItemId = R.id.action_add
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                router.navigate(AppDestination(DestinationModel(Destination.RED.name, null), null))
                return true
            }
            R.id.action_green -> {
                router.navigate(
                    AppDestination(
                        DestinationModel(Destination.GREEN.name, null),
                        null
                    )
                )
                return true
            }
        }

        return false
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        when (item.itemId) {
            R.id.action_add -> router.navigate(
                AppDestination(
                    DestinationModel(
                        Destination.RED.name,
                        null
                    ), null
                )
            )
            R.id.action_green -> router.navigate(
                AppDestination(
                    DestinationModel(
                        Destination.GREEN.name,
                        null
                    ), null
                )
            )
        }
    }

    override fun getContainer(): RoutableContainer? = parentFragment as? RoutableContainer

    //    private fun openFragment(fragmentProvider: () -> Fragment, tag: String) {
//        with(supportFragmentManager) {
//            beginTransaction()
//                .run {
//                    fragments.forEach { hide(it) }
//                    findFragmentByTag(tag)
//                        ?.run { show(this) }
//                        ?: add(com.example.docodemo.R.id.content, fragmentProvider(), tag)
//                }.commit()
//        }
//    }
}
