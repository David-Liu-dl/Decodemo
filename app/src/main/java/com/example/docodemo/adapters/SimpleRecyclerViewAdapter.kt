package com.example.docodemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.docodemo.R
import com.example.docodemo.models.NumberCell
import kotlinx.android.synthetic.main.item_simple.view.*

class SimpleRecyclerViewAdapter(
    private val context: Context,
    private val cells: List<NumberCell>,
    private val onSelected: ((cell: NumberCell) -> Unit)? = null
) :
    RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_simple, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell = cells[position]

        with(holder.itemView) {
            text_view.text = cell.number.toString()
            text_view.setOnClickListener {
                holder.itemView.isSelected = true
                onSelected?.run { this(cell) }
            }
        }
    }

    override fun getItemCount(): Int = cells.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            with(itemView.text_view) {
                setTextColor(ContextCompat.getColor(context, R.color.white))
            }
        }
    }
}
