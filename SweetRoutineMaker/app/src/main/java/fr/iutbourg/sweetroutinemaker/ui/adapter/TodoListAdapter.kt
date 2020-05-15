package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.Options
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.ItemTodoViewHolder
import fr.iutbourg.sweetroutinemaker.ui.fragment.UserActionOnList

class TodoListAdapter(private val userActionOnList: UserActionOnList) : BaseItemAdapter<Options, ItemTodoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.option_item_viewholder, parent, false)
        return ItemTodoViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemTodoViewHolder, position: Int) {
        holder.bind(itemList[position]){ option ->
            holder.itemView.setOnClickListener {
            }
        }
    }
}


