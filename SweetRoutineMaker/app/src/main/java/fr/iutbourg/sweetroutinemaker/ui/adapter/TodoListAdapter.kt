package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.view.ViewGroup
import com.bumptech.glide.Glide
import fr.iutbourg.sweetroutinemaker.data.model.Options
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.ItemTodoViewHolder
import kotlinx.android.synthetic.main.option_item_viewholder.view.*

class TodoListAdapter : BaseItemAdapter<Options, ItemTodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTodoViewHolder =
        ItemTodoViewHolder.create(parent)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemTodoViewHolder, position: Int) {
        val option = itemList[position]

        holder.bind(option) { opt ->
            holder.itemView.apply {
                opt.todoItemB64?.let {
                    Glide.with(this)
                        .load(it)
                        .into(todo_image_view)
                }
            }
        }
    }
}


