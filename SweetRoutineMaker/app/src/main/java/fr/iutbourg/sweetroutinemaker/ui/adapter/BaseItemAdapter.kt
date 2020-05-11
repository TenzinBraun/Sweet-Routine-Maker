package fr.iutbourg.sweetroutinemaker.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.BaseItemViewHolder

abstract class BaseItemAdapter<T, R: BaseItemViewHolder<T>> : RecyclerView.Adapter<R>() {
    abstract fun submitList(list: List<T>)
    protected abstract var itemList: List<T>
}
