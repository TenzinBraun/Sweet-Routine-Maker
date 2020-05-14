package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.BaseItemViewHolder

abstract class BaseItemAdapter<Model, Holder: BaseItemViewHolder<Model>> : RecyclerView.Adapter<Holder>() {

    open fun submitList(list: List<Model>){
        itemList = list
    }

    protected open var itemList: List<Model> = emptyList()
}
