package fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.iutbourg.sweetroutinemaker.R

class AddTagHolder(itemView: View) : BaseItemViewHolder<String>(itemView) {

    override fun bind(element: String, block: (String) -> Unit) {
        return block(element)
    }

    companion object Instance {
        fun create(parent: ViewGroup): AddTagHolder {
            return AddTagHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.tag_item_holder, parent, false)
            )
        }
    }
}
