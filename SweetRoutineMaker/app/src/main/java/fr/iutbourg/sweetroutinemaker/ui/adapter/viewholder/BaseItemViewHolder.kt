package fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseItemViewHolder<Model> constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(element: Model, block: (Model) -> Unit)
}

