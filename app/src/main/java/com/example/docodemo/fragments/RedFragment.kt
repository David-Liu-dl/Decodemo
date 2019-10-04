package com.example.docodemo.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.docodemo.R
import com.example.docodemo.adapters.SimpleRecyclerViewAdapter
import com.example.docodemo.fragments.containers.RoutableContainer
import com.example.docodemo.fragments.containers.RoutableContent
import com.example.docodemo.fragments.containers.Routable
import com.example.docodemo.mappers.Destination
import com.example.docodemo.models.NumberCell
import com.example.docodemo.routing.*
import kotlinx.android.synthetic.main.fragment_red.view.*
import kotlinx.android.synthetic.main.layout_toolbar.toolbar_actionbar

class RedFragment : Fragment(), RoutableContent, Routable {
    override val router: Router by lazy { Router.makeRouter(getContainer()) }

    companion object {
        const val TAG = "RedFragment"

        private const val NUMBER_OF_CELL = 20

        fun createInstance() = RedFragment()
    }

    private val cells = (0 until NUMBER_OF_CELL).map { NumberCell(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_red, container, false).apply {
        recycler_view.apply {
            val mLayoutManager = LinearLayoutManager(context)

            layoutManager = mLayoutManager
            adapter = SimpleRecyclerViewAdapter(context, cells) {
                val route = AppDestination(DestinationModel(Destination.BLUE.name, null), null)
                router.navigate(route)
            }
            addItemDecoration(DividerItemDecoration(context, mLayoutManager.orientation))
            setBackgroundResource(R.color.red)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        toolbar_actionbar.setTitle(R.string.fragment_label_red)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar_actionbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.toolbar_items_red_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_add -> {
                val route = AppDestination(DestinationModel(Destination.RED.name, null), null)
                router.navigate(route)
            }
            R.id.action_adjust -> {
                val route = AppDestination(DestinationModel(Destination.BLUE.name, null), null)
                router.navigate(route)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun getContainer(): RoutableContainer? = getContainer(parentFragment)

    private fun getContainer(parentFragment: Fragment?): RoutableContainer? {
        if (parentFragment != null) {
            return (parentFragment as? RoutableContainer)
                ?.let { it } ?: getContainer(parentFragment)
        }

        if (activity != null) {
            return (activity as? RoutableContainer)
                ?.let { it }
        }

        return null
    }
}
