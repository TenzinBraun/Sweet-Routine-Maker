package fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.SectionItem
import fr.iutbourg.testcustomrecyclerview.SectionItemAdapter
import kotlinx.android.synthetic.main.view_holder.view.*

class SectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private var adapter = SectionItemAdapter()

    fun bindView(item: SectionItem) {
//        itemView.sectionName.text = item.listItem.
        itemView.itemRecycler.apply {
            adapter = this@SectionViewHolder.adapter
            layoutManager = LinearLayoutManager(context)
        }
        adapter.submitList(item.listItem)
    }

    companion object {
        fun create(parent: ViewGroup): SectionViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder, parent, false)
            return SectionViewHolder(view)
        }
    }
}
