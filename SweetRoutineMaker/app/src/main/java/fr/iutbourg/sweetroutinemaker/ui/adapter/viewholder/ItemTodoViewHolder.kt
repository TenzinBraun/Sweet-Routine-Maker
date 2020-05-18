package fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.Options

class ItemTodoViewHolder(itemView: View) : BaseItemViewHolder<Options>(itemView){

    companion object {
        fun create(parent: ViewGroup): ItemTodoViewHolder {
            val view = LayoutInflater.from(
                parent.context
            ).inflate(R.layout.option_item_viewholder, parent, false)

            return ItemTodoViewHolder(view)
        }
    }
}
