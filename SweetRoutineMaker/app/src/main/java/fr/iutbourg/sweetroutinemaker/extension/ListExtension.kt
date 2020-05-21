package fr.iutbourg.sweetroutinemaker.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.iutbourg.sweetroutinemaker.data.model.Options
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

fun List<Options>.filterByName(): List<Options> {
    return this.sortedBy {
        it.todoItemLabel
    }
}

fun List<Options>.filterByNameDesc(): List<Options> {
    return this.sortedByDescending {
        it.todoItemLabel
    }
}

fun <Model, Holder : BaseItemViewHolder<Model>> List<Model>.initRecyclerView(itemViewModel: BaseViewModel<Model>, itemListAdapter: BaseItemAdapter<Model, Holder>): LiveData<List<Model>> {
    return if(this.isEmpty()){
        itemViewModel.getItemList()
    }
    else {
        itemListAdapter.submitList(this)
        MutableLiveData()
    }
}

class GenericList<T>(val genericType: Class<T>) : ArrayList<T>()



