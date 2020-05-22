package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.SectionViewHolder
import fr.iutbourg.sweetroutinemaker.data.model.SectionItem

class SectionAdapter : RecyclerView.Adapter<SectionViewHolder>() {

    private var itemList = emptyList<SectionItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder =
        SectionViewHolder.create(parent)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bindView(itemList[position])
    }

    fun submitList(sections: MutableList<SectionItem>) {
        itemList = sections
        notifyDataSetChanged()
    }

}
