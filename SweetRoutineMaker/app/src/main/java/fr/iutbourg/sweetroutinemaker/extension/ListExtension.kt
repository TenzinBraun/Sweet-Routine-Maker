package fr.iutbourg.sweetroutinemaker.extension

import fr.iutbourg.sweetroutinemaker.data.model.TodoItem
import fr.iutbourg.sweetroutinemaker.ui.adapter.BaseItemAdapter
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.BaseItemViewHolder
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.BaseViewModel


fun <T> List<T>.addElement(element: T): List<T> {
    (this as MutableList).add(element)
    return this
}

fun <T> List<T>.removeElement(element: T): List<T> {
    (this as MutableList).remove(element)
    return this
}

fun <T> List<T>.removeElement(position: Int): List<T> {
    (this as MutableList).removeAt(position)
    return this
}

fun List<TodoItem>.filterByName(): List<TodoItem> {
    return this.sortedBy {
        it.todoItemLabel
    }
}

fun List<TodoItem>.filterByNameDesc(): List<TodoItem> {
    return this.sortedByDescending {
        it.todoItemLabel
    }
}

fun <T, R : BaseItemViewHolder<T>> List<T>.initRecyclerView(itemViewModel: BaseViewModel, itemListAdapter: BaseItemAdapter<T, R>){
    if(this.isEmpty()){
        itemViewModel.getItemList()
    }
    else
        itemListAdapter.submitList(this)
}



