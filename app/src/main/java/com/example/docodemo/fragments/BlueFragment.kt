package com.example.docodemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.docodemo.R
import com.example.docodemo.adapters.SimpleRecyclerViewAdapter
import com.example.docodemo.fragments.containers.RoutableContainer
import com.example.docodemo.fragments.containers.RoutableContent
import com.example.docodemo.models.NumberCell
import com.example.docodemo.routing.Router
import kotlinx.android.synthetic.main.fragment_red.view.*

class BlueFragment : Fragment(), RoutableContent {
    override val router: Router = Router.makeRouter(getContainer())

    companion object {
        const val TAG = "RedFragment"

        private const val NUMBER_OF_CELL = 20

        fun createInstance(
            initiallySelected: Int?,
            items: List<String>,
            selectItem: (Int) -> Unit
        ): BlueFragment {
            val instance = BlueFragment()

            instance.initiallySelected = initiallySelected
            instance.items = items
            instance.selectItem = selectItem

            return instance
        }
    }

    var initiallySelected: Int? = null
    var items: List<String>? = null
    var selectItem: ((Int) -> Unit)? = null

    private val cells = (0 until NUMBER_OF_CELL).map { NumberCell(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_blue, container, false).apply {
        recycler_view.apply {
            val mLayoutManager = LinearLayoutManager(context)

            layoutManager = mLayoutManager
            adapter = SimpleRecyclerViewAdapter(context, cells) { selectItem?.invoke(it.number) }
            addItemDecoration(DividerItemDecoration(context, mLayoutManager.orientation))
            setBackgroundResource(R.color.blue)
        }
    }

    override fun getContainer(): RoutableContainer? = getContainerFragment(parentFragment)

    private fun getContainerFragment(parentFragment: Fragment?): RoutableContainer? {
        if (parentFragment != null) {
            return (parentFragment as? RoutableContainer)
                .let { it } ?: getContainerFragment(parentFragment)
        }

        return null
    }
}
