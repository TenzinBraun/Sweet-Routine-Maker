package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.view.ViewGroup
import com.bumptech.glide.Glide
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.ActivityTodoListViewHolder
import fr.iutbourg.sweetroutinemaker.ui.fragment.ActivityTodoClickListener
import fr.iutbourg.sweetroutinemaker.ui.fragment.ChildClickListener
import kotlinx.android.synthetic.main.activity_todo_view_holder.view.*
import java.util.*

class ActivityTodoListAdapter(
    override var itemList: List<TodoList>,
    private var clickListener: ActivityTodoClickListener
) : BaseItemAdapter<TodoList, ActivityTodoListViewHolder>() {



    override fun submitList(list: List<TodoList>) {
        this.itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityTodoListViewHolder = ActivityTodoListViewHolder.create(parent)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ActivityTodoListViewHolder, position: Int) {
        val todo = itemList[position]

        holder.bind(todo) { todoList ->
            holder.itemView.apply {
                activity_todo_name.text = todoList.listLabel
                Glide.with(this)
                    .load(todo.listBase64)
                    .into(activity_todo_image_view)

                setOnClickListener {
                    clickListener.onActivityTodoClickListener(todo.items!!)
                }
            }
        }
    }
}