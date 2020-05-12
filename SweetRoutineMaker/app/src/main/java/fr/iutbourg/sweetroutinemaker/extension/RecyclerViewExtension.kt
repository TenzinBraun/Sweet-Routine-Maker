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

fun <Model, Holder: BaseItemViewHolder<Model>> BaseItemAdapter<Model, Holder>.notifyAllOnDataSetChanged() {
    notifyDataSetChanged()
}

fun <Model, Holder: BaseItemViewHolder<Model>> RecyclerView.applyRequire(todoListAdapter: BaseItemAdapter<Model, Holder>, manager: RecyclerView.LayoutManager){
    this.apply {
        adapter = todoListAdapter
        layoutManager = manager
    }
}