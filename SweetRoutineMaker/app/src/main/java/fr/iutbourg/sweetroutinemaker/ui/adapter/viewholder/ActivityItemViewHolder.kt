package fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo

class ActivityItemViewHolder(itemView: View) : BaseItemViewHolder<ActivityTodo>(itemView) {

    companion object {
        fun create(parent: ViewGroup): ActivityItemViewHolder {
            val view = LayoutInflater.from(
                parent.context
            ).inflate(R.layout.activity_view_holder, parent, false)

            return ActivityItemViewHolder(view)
        }
    }


}
