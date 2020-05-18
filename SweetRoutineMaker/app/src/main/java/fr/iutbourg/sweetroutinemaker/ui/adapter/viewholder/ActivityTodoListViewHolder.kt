package fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.TodoList

class ActivityTodoListViewHolder(itemView: View): BaseItemViewHolder<TodoList>(itemView){

    companion object {
        fun create(parent: ViewGroup): ActivityTodoListViewHolder {
            val view = LayoutInflater.from(
                parent.context
            ).inflate(R.layout.activity_todo_view_holder, parent, false) // TODO change R.layout.viewHolder

            return ActivityTodoListViewHolder(view)
        }
    }
}
