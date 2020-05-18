package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.view.ViewGroup
import com.bumptech.glide.Glide
import fr.iutbourg.sweetroutinemaker.data.model.Options
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.ItemTodoViewHolder
import kotlinx.android.synthetic.main.activity_todo_view_holder.view.*
import kotlinx.android.synthetic.main.option_item_viewholder.view.*

class TodoListAdapter(
    override var itemList: List<Options>
) : BaseItemAdapter<Options, ItemTodoViewHolder>() {

    override fun submitList(list: List<Options>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTodoViewHolder = ItemTodoViewHolder.create(parent)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemTodoViewHolder, position: Int) {
       val option = itemList[position]

        holder.bind(option) { opt ->
            holder.itemView.apply {
                Glide.with(this)
                    .load(opt.todoItemB64)
                    .into(todo_image_view)
            }
        }
    }




}


