package fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.ChildProfile
import fr.iutbourg.sweetroutinemaker.data.model.Options
import kotlinx.android.synthetic.main.child_view_holder.view.*

class ChildItemViewHolder(itemView: View): BaseItemViewHolder<ChildProfile>(itemView) {

    companion object {
        fun create(parent: ViewGroup): ChildItemViewHolder {
            val view = LayoutInflater.from(
                parent.context
            ).inflate(R.layout.child_view_holder, parent, false)

            return ChildItemViewHolder(view)
        }
    }
}