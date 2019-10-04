package com.example.docodemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.docodemo.fragments.containers.ContainerFragment
import com.example.docodemo.fragments.containers.RoutableContainer
import com.example.docodemo.mappers.Destination
import com.example.docodemo.routing.AppDestination
import com.example.docodemo.routing.DestinationModel
import com.example.docodemo.routing.Router

class MainActivity : AppCompatActivity(), RoutableContainer {

    override val router: Router = Router.makeRouter(this)
    override val containerFragmentManager: FragmentManager = supportFragmentManager
    override val containerType: RoutableContainer.ContainerType =
        RoutableContainer.ContainerType.FullSize

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content, ContainerFragment.createInstance(), ContainerFragment.TAG)
            .commit()
    }

    override fun onBackPressed() {
        if (!handleOnBackPressed(supportFragmentManager))
            super.onBackPressed()
    }

    private fun handleOnBackPressed(childFragmentManager: FragmentManager): Boolean {
        // handle child fragments first
        for (frag in childFragmentManager.fragments) {
            if (frag.isVisible && handleOnBackPressed(frag.childFragmentManager)) {
                return true
            }
        }
        // if no child fragments need to be handled, then try to consume event by itself
        if (childFragmentManager.backStackEntryCount > 0) {
            childFragmentManager.popBackStack()
            return true
        }

        return false
    }
}
