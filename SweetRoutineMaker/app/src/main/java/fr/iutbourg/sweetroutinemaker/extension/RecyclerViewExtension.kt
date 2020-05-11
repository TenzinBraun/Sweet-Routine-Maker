package fr.iutbourg.sweetroutinemaker.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.sweetroutinemaker.ui.adapter.BaseItemAdapter
import fr.iutbourg.sweetroutinemaker.ui.adapter.TodoListAdapter
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.BaseItemViewHolder


fun RecyclerView.onValidationTodoItem(
    position: Int,
    block: RecyclerView.() -> Unit
) {
    smoothScrollToPosition(position)
    return block()
}

fun <T, R: BaseItemViewHolder<T>> BaseItemAdapter<T, R>.notifyAllOnDataSetChanged() {
    notifyDataSetChanged()
}

fun <T, R: BaseItemViewHolder<T>> RecyclerView.applyRequire(todoListAdapter: BaseItemAdapter<T, R>, manager: LinearLayoutManager){
    this.apply {
        adapter = todoListAdapter
        layoutManager = manager
    }
}