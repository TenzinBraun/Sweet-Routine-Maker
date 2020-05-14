package fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder

import android.view.View
import fr.iutbourg.sweetroutinemaker.data.model.TodoList

class ActivityTodoListViewHolder(itemView: View): BaseItemViewHolder<TodoList>(itemView){

    override fun bind(element: TodoList, block: (TodoList) -> Unit) {
        return block(element)
    }


}
