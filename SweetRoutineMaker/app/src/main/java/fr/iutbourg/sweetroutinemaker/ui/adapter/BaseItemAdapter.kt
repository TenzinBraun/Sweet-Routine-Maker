package fr.iutbourg.sweetroutinemaker.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.BaseItemViewHolder

abstract class BaseItemAdapter<Model, Holder: BaseItemViewHolder<Model>> : RecyclerView.Adapter<Holder>() {
    abstract fun submitList(list: List<Model>)
    protected abstract var itemList: List<Model>
}
