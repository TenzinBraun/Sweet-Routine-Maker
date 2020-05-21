package fr.iutbourg.testcustomrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.sweetroutinemaker.R
import kotlinx.android.synthetic.main.item_view_holder.view.*

class SectionItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(item: String) {
//        itemView.textName.text = item
        itemView.setOnClickListener {
            Toast.makeText(itemView.context, item, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun create(parent: ViewGroup): SectionItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_holder, parent, false)
            return SectionItemViewHolder(view)
        }
    }

}
