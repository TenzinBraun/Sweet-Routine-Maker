package fr.iutbourg.testcustomrecyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SectionItemAdapter : RecyclerView.Adapter<SectionItemViewHolder>() {

    private var items = emptyList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionItemViewHolder
    = SectionItemViewHolder.create(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SectionItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submitList(listItem: List<String>) {
        items = listItem
        notifyDataSetChanged()
    }

}
