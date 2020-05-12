package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.view.ViewGroup
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.ActivityTodoListViewHolder

class ActivityTodoListAdapter : BaseItemAdapter<TodoList, ActivityTodoListViewHolder>() {

    override var itemList: List<TodoList> = emptyList()

    override fun submitList(list: List<TodoList>) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityTodoListViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ActivityTodoListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}