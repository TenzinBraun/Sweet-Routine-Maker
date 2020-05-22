package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.SectionViewHolder
import fr.iutbourg.sweetroutinemaker.data.model.SectionItem
import java.lang.Exception

class SectionAdapter : RecyclerView.Adapter<SectionViewHolder>() {

    private var itemList = emptyList<SectionItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder =
        SectionViewHolder.create(parent)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        try {
            val item = itemList[position]
            holder.bindView(item)
        }catch (e: Exception){
            try {
                val item = (itemList[position] as HashMap<String, ArrayList<String>>)["listItem"]
                holder.bindView(SectionItem(item!!))
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun submitList(sections: List<SectionItem>) {
        itemList = sections
        notifyDataSetChanged()
    }

}
